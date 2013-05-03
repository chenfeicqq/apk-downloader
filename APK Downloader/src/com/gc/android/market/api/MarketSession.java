package com.gc.android.market.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.gc.android.market.api.common.Base64;
import com.gc.android.market.api.exception.HttpException;
import com.gc.android.market.api.exception.LoginException;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.CategoriesRequest;
import com.gc.android.market.api.model.Market.CategoriesResponse;
import com.gc.android.market.api.model.Market.CommentsRequest;
import com.gc.android.market.api.model.Market.CommentsResponse;
import com.gc.android.market.api.model.Market.GetAssetRequest;
import com.gc.android.market.api.model.Market.GetAssetResponse;
import com.gc.android.market.api.model.Market.GetImageRequest;
import com.gc.android.market.api.model.Market.GetImageResponse;
import com.gc.android.market.api.model.Market.Request;
import com.gc.android.market.api.model.Market.Request.RequestGroup;
import com.gc.android.market.api.model.Market.RequestContext;
import com.gc.android.market.api.model.Market.Response;
import com.gc.android.market.api.model.Market.Response.ResponseGroup;
import com.gc.android.market.api.model.Market.ResponseContext;

;

/**
 * MarketSession session = new MarketSession();
 * session.login(login,password, androidId);
 * For asyncronous calls use append, callback and flush
 * session.append(xxx,yyy);
 * session.append(xxx,yyy);
 * ...
 * session.flush();
 *  For syncronous call, use the specific method
 */
public class MarketSession
{

    public static interface Callback<T>
    {

        public void onResult(ResponseContext context, T response);
    }

    /*
     * SERVICE : Service required to the market. 
     * Default value: android. This service must be used to query info to the Market
     * androidsecure: This service must be used to download apps
     * sierra (checkout): This service must be used for checkout (at moment unused)
     */

    public String SERVICE = "android";

    private static final String URL_LOGIN = "https://www.google.com/accounts/ClientLogin";

    public static final String ACCOUNT_TYPE_GOOGLE = "GOOGLE";

    public static final String ACCOUNT_TYPE_HOSTED = "HOSTED";

    public static final String ACCOUNT_TYPE_HOSTED_OR_GOOGLE = "HOSTED_OR_GOOGLE";

    public static final int PROTOCOL_VERSION = 2;

    Request.Builder request = Request.newBuilder();

    RequestContext.Builder context = RequestContext.newBuilder();

    public RequestContext.Builder getContext()
    {
        return this.context;
    }

    List<Callback<?>> callbacks = new Vector<Callback<?>>();

    String authSubToken = null;

    public String getAuthSubToken()
    {
        return this.authSubToken;
    }

    /*
     * Login must set isSecure to false for list and download
     */
    public MarketSession(final Boolean isSecure)
    {
        if (isSecure)
        {
            this.SERVICE = "androidsecure";
        }
        else
        {
            this.SERVICE = "android";
        }
        this.context.setIsSecure(false);
        this.context.setVersion(2009011);
        this.setLocale(Locale.getDefault());
        this.context.setDeviceAndSdkVersion("passion:9");
        this.setOperatorTMobile();
    }

    public void setLocale(final Locale locale)
    {
        this.context.setUserLanguage(locale.getLanguage().toLowerCase());
        this.context.setUserCountry(locale.getCountry().toLowerCase());
    }

    public void setOperator(final String alpha, final String numeric)
    {
        this.setOperator(alpha, alpha, numeric, numeric);
    }

    public void setOperatorTMobile()
    {
        this.setOperator("T-Mobile", "310260");
    }

    public void setOperatorSFR()
    {
        this.setOperator("F SFR", "20810");
    }

    public void setOperatorO2()
    {
        this.setOperator("o2 - de", "26207");
    }

    public void setOperatorSimyo()
    {
        this.setOperator("E-Plus", "simyo", "26203", "26203");
    }

    public void setOperatorSunrise()
    {
        this.setOperator("sunrise", "22802");
    }

    /**
     * http://www.2030.tk/wiki/Android_market_switch
     */
    public void setOperator(final String alpha, final String simAlpha, final String numeric, final String simNumeric)
    {
        this.context.setOperatorAlpha(alpha);
        this.context.setSimOperatorAlpha(simAlpha);
        this.context.setOperatorNumeric(numeric);
        this.context.setSimOperatorNumeric(simNumeric);
    }

    public void setAuthSubToken(final String authSubToken)
    {
        this.context.setAuthSubToken(authSubToken);
        this.authSubToken = authSubToken;
    }

    public void setIsSecure(final Boolean isSecure)
    {
        this.context.setIsSecure(isSecure);
    }

    public void setAndroidId(final String androidId)
    {
        this.context.setAndroidId(androidId);
    }

    public void login(final String email, final String password, final String androidId)
    {
        this.login(email, password, androidId, ACCOUNT_TYPE_HOSTED_OR_GOOGLE);
    }

    public void login(final String email, final String password, final String androidId, final Proxy proxy)
    {
        this.login(email, password, androidId, ACCOUNT_TYPE_HOSTED_OR_GOOGLE, proxy);
    }

    public void login(final String email, final String password, final String androidId, final String accountType)
    {
        this.login(email, password, androidId, accountType, null);
    }

    public void login(final String email, final String password, final String androidId, final String accountType,
        final Proxy proxy)
    {
        //Android ID must an unique identifier associated to the account 
        //used in in the login
        this.setAndroidId(androidId);
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("Email", email);
        params.put("Passwd", password);

        params.put("service", this.SERVICE);
        //	params.put("source", source);
        params.put("accountType", accountType);

        // Login at Google.com
        try
        {
            String data = this.postUrl(URL_LOGIN, params, proxy);
            StringTokenizer st = new StringTokenizer(data, "\n\r=");
            String authKey = null;
            while (st.hasMoreTokens())
            {
                if (st.nextToken().equalsIgnoreCase("Auth"))
                {
                    authKey = st.nextToken();
                    break;
                }
            }
            if (authKey == null)
            {
                throw new RuntimeException("authKey not found in " + data);
            }

            this.setAuthSubToken(authKey);
        }
        catch (HttpException httpEx)
        {
            if (httpEx.getErrorCode() != 403)
            {
                throw httpEx;
            }

            String data = httpEx.getErrorData();
            StringTokenizer st = new StringTokenizer(data, "\n\r=");
            String googleErrorCode = null;
            while (st.hasMoreTokens())
            {
                if (st.nextToken().equalsIgnoreCase("Error"))
                {
                    googleErrorCode = st.nextToken();
                    break;
                }
            }
            if (googleErrorCode == null)
            {
                throw httpEx;
            }

            throw new LoginException(googleErrorCode);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public String postUrl(final String url, final Map<String, String> params, final Proxy proxy)
        throws IOException
    {
        String data = "";
        for (String key : params.keySet())
        {
            data += "&" + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(params.get(key), "UTF-8");
        }
        data = data.substring(1);

        // System.out.println(url + "?" + data);

        // System.out.println(data);
        // Make the connection to Authoize.net
        URL aURL = new java.net.URL(url);
        HttpURLConnection aConnection = null;
        if (null == proxy)
        {
            aConnection = (java.net.HttpURLConnection)aURL.openConnection();
        }
        else
        {
            aConnection = (java.net.HttpURLConnection)aURL.openConnection(proxy);
        }
        try
        {
            aConnection.setDoOutput(true);
            aConnection.setDoInput(true);
            aConnection.setRequestMethod("POST");
            //aConnection.setAllowUserInteraction(false);
            // POST the data
            OutputStreamWriter streamToAuthorize = new java.io.OutputStreamWriter(aConnection.getOutputStream());
            streamToAuthorize.write(data);
            streamToAuthorize.flush();
            streamToAuthorize.close();

            // check error
            int errorCode = aConnection.getResponseCode();
            if (errorCode >= 400)
            {
                InputStream errorStream = aConnection.getErrorStream();
                try
                {
                    String errorData = this.streamToString(errorStream);
                    throw new HttpException(errorCode, errorData);
                }
                finally
                {
                    errorStream.close();
                }
            }

            // Get the Response
            InputStream resultStream = aConnection.getInputStream();
            try
            {
                String responseData = this.streamToString(resultStream);
                return responseData;
            }
            finally
            {
                resultStream.close();
            }
        }
        finally
        {
            aConnection.disconnect();
        }
    }

    private String streamToString(final InputStream resultStream)
        throws IOException
    {
        BufferedReader aReader = new java.io.BufferedReader(new java.io.InputStreamReader(resultStream));
        StringBuffer aResponse = new StringBuffer();
        String aLine = aReader.readLine();
        while (aLine != null)
        {
            aResponse.append(aLine + "\n");
            aLine = aReader.readLine();
        }
        return aResponse.toString();

    }

    public List<Object> queryApp(final AppsRequest requestGroup)
    {
        List<Object> retList = new ArrayList<Object>();

        this.request.addRequestGroup(RequestGroup.newBuilder().setAppsRequest(requestGroup));

        RequestContext ctxt = this.context.build();
        this.context = RequestContext.newBuilder(ctxt);
        this.request.setContext(ctxt);
        try
        {
            Response resp = this.executeProtobuf(this.request.build());
            for (ResponseGroup grp : resp.getResponseGroupList())
            {
                if (grp.hasAppsResponse())
                {
                    retList.add(grp.getAppsResponse());
                }
            }
        }
        finally
        {
            this.request = Request.newBuilder();
        }
        return retList;
    }

    public CategoriesResponse queryCategories()
    {
        RequestContext ctxt = this.context.build();
        this.context = RequestContext.newBuilder(ctxt);
        this.request.setContext(ctxt);
        CategoriesResponse categoriesResponse = null;
        try
        {
            Response response =
                this.executeProtobuf(this.request.addRequestGroup(RequestGroup.newBuilder()
                    .setCategoriesRequest(CategoriesRequest.newBuilder().build()))
                    .setContext(ctxt)
                    .build());
            categoriesResponse = response.getResponseGroup(0).getCategoriesResponse();
        }
        finally
        {
            this.request = Request.newBuilder();
            ;
        }
        return categoriesResponse;
    }

    public GetAssetResponse queryGetAssetRequest(final String assetId)
    {
        this.setIsSecure(true);
        RequestContext ctxt = this.context.build();
        this.context = RequestContext.newBuilder(ctxt);
        this.request.setContext(ctxt);
        GetAssetResponse assetResponse = null;
        try
        {
            Response response =
                this.executeProtobuf(this.request.addRequestGroup(RequestGroup.newBuilder()
                    .setGetAssetRequest(GetAssetRequest.newBuilder().setAssetId(assetId).build()))
                    .setContext(ctxt)
                    .build());
            assetResponse = response.getResponseGroup(0).getGetAssetResponse();
        }
        finally
        {
            this.setIsSecure(false);
            this.request = Request.newBuilder();
        }
        return assetResponse;
    }

    public void append(final AppsRequest requestGroup, final Callback<AppsResponse> responseCallback)
    {
        this.request.addRequestGroup(RequestGroup.newBuilder().setAppsRequest(requestGroup));
        this.callbacks.add(responseCallback);
    }

    public void append(final GetImageRequest requestGroup, final Callback<GetImageResponse> responseCallback)
    {
        this.request.addRequestGroup(RequestGroup.newBuilder().setImageRequest(requestGroup));
        this.callbacks.add(responseCallback);
    }

    public void append(final CommentsRequest requestGroup, final Callback<CommentsResponse> responseCallback)
    {
        this.request.addRequestGroup(RequestGroup.newBuilder().setCommentsRequest(requestGroup));
        this.callbacks.add(responseCallback);
    }

    public void append(final CategoriesRequest requestGroup, final Callback<CategoriesResponse> responseCallback)
    {
        this.request.addRequestGroup(RequestGroup.newBuilder().setCategoriesRequest(requestGroup));
        this.callbacks.add(responseCallback);
    }

    @SuppressWarnings("unchecked")
    public void flush()
    {
        RequestContext ctxt = this.context.build();
        this.context = RequestContext.newBuilder(ctxt);
        this.request.setContext(ctxt);
        try
        {
            Response resp = this.executeProtobuf(this.request.build());
            int i = 0;
            for (ResponseGroup grp : resp.getResponseGroupList())
            {
                Object val = null;
                if (grp.hasAppsResponse())
                {
                    val = grp.getAppsResponse();
                }
                if (grp.hasCategoriesResponse())
                {
                    val = grp.getCategoriesResponse();
                }
                if (grp.hasCommentsResponse())
                {
                    val = grp.getCommentsResponse();
                }
                if (grp.hasImageResponse())
                {
                    val = grp.getImageResponse();
                }
                ((Callback<Object>)this.callbacks.get(i)).onResult(grp.getContext(), val);
                i++;
            }
        }
        finally
        {
            this.request = Request.newBuilder();
            this.callbacks.clear();
        }
    }

    public ResponseGroup execute(final RequestGroup requestGroup)
    {
        RequestContext ctxt = this.context.build();
        this.context = RequestContext.newBuilder(ctxt);
        this.request.setContext(ctxt);
        Response resp = this.executeProtobuf(this.request.addRequestGroup(requestGroup).setContext(ctxt).build());
        return resp.getResponseGroup(0);
    }

    private Response executeProtobuf(final Request request)
    {
        byte[] requestBytes = request.toByteArray();
        byte[] responseBytes = null;
        try
        {
            if (!this.context.getIsSecure())
            {
                responseBytes = this.executeRawHttpQuery(requestBytes);
            }
            else
            {
                responseBytes = this.executeRawHttpsQuery(requestBytes);
            }
            Response r = Response.parseFrom(responseBytes);
            return r;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private byte[] executeRawHttpQuery(final byte[] request)
    {
        try
        {

            URL url = new URL("http://android.clients.google.com/market/api/ApiRequest");
            HttpURLConnection cnx = (HttpURLConnection)url.openConnection();
            cnx.setDoOutput(true);
            cnx.setRequestMethod("POST");
            cnx.setRequestProperty("Cookie", "ANDROID=" + this.authSubToken);
            cnx.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
            cnx.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            cnx.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");

            String request64 = Base64.encodeBytes(request, Base64.URL_SAFE);

            String requestData = "version=" + PROTOCOL_VERSION + "&request=" + request64;

            cnx.setFixedLengthStreamingMode(requestData.getBytes("UTF-8").length);
            OutputStream os = cnx.getOutputStream();
            os.write(requestData.getBytes());
            os.close();

            if (cnx.getResponseCode() >= 400)
            {
                throw new RuntimeException("Response code = " + cnx.getResponseCode() + ", msg = "
                    + cnx.getResponseMessage());
            }

            InputStream is = cnx.getInputStream();
            GZIPInputStream gzIs = new GZIPInputStream(is);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            while (true)
            {
                int nb = gzIs.read(buff);
                if (nb < 0)
                {
                    break;
                }
                bos.write(buff, 0, nb);
            }
            is.close();
            cnx.disconnect();

            return bos.toByteArray();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private Boolean trustAll()
    {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager()
        {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return null;
            }

            @Override
            public void checkClientTrusted(final java.security.cert.X509Certificate[] certs, final String authType)
            {
            }

            @Override
            public void checkServerTrusted(final java.security.cert.X509Certificate[] certs, final String authType)
            {
            }
        }};
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
            {
                @Override
                public boolean verify(final String arg0, final SSLSession arg1)
                {
                    return true;
                }
            });
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private byte[] executeRawHttpsQuery(final byte[] request)
    {
        if (request == null)
        {
            return null;
        }
        if (!this.trustAll())
        {
            return null;
        }
        try
        {
            URL url = new URL("https://android.clients.google.com/market/api/ApiRequest");
            HttpsURLConnection cnx = (HttpsURLConnection)url.openConnection();
            cnx.setDoOutput(true);
            cnx.setRequestMethod("POST");
            cnx.setRequestProperty("Cookie", "ANDROIDSECURE=" + this.getAuthSubToken());
            cnx.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
            cnx.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            cnx.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            String request64 = Base64.encodeBytes(request, Base64.URL_SAFE);
            String requestData = "version=" + PROTOCOL_VERSION + "&request=" + request64;
            cnx.setFixedLengthStreamingMode(requestData.getBytes("UTF-8").length);
            OutputStream os = cnx.getOutputStream();
            os.write(requestData.getBytes());
            os.close();
            if (cnx.getResponseCode() >= 400)
            {
                cnx.disconnect();
                throw new IOException("Response code = " + cnx.getResponseCode() + ", msg = "
                    + cnx.getResponseMessage());
            }
            InputStream is = cnx.getInputStream();
            GZIPInputStream gzIs = new GZIPInputStream(is);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            while (true)
            {
                int nb = gzIs.read(buff);
                if (nb < 0)
                {
                    break;
                }
                bos.write(buff, 0, nb);
            }
            is.close();
            cnx.disconnect();
            return bos.toByteArray();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}

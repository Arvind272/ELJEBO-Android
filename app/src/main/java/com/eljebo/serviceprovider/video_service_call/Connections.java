package com.eljebo.serviceprovider.video_service_call;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Nimisha Bansal on 12/3/16.
 */
public class Connections {

    public static final int CONNECTION_TIMEOUT = 60000;
    public static final int DATARETRIEVAL_TIMEOUT = 60000;

    static String basicAuthDefault = "Basic " + "QzZFOUFFNjQxRDk4RUI0RjlGNDQzM0YzM0E5RURFN0Y1NjMxNUNGNzQ1ODEwMUQxNERGNDI0ODg1QjA4MzUwRjpOWnc2R3RCbzc4b2d1R3Z1bnpDeWF3OTM0SkN1enZ0YnNydlQxN3Bn";

    public static String requestPostUrl(String url, JSONObject data, Boolean isRegistrationActivity) {
        String postParameters = data.toString();

        String basicAuth;
        String userCredentials = Constant.api_key + ":" + Constant.secret_key;
        try {
            basicAuth = "Basic " + new String(Base64.encodeToString(userCredentials.getBytes("UTF-8"), Base64.DEFAULT));
        } catch (Exception unsupportedException) {
            basicAuth = basicAuthDefault;
        }

//      disableConnectionReuseIfNecessary();
        basicAuth = basicAuthDefault;

        //Log.e("+++++++++++basicAuth:", " " + basicAuth);

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(url);
            Log.e("+++++++++++Post URL:", " " + url);

            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            if (isRegistrationActivity)
                urlConnection.setRequestProperty("Authorization", basicAuth);
            else
                urlConnection.setRequestProperty("Authorization", "");

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConnection.setDoOutput(true);

            // handle POST parameters
            if (postParameters != null) {
                urlConnection.setFixedLengthStreamingMode(postParameters.getBytes().length);

                //send the POST out
                PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                out.print(postParameters);
                out.close();
            }

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("+++++++++++statusCode:", " " + statusCode);
                // throw some exception
                return "server problem";
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            // handle invalid URL
            return "MalformedURLException";
        } catch (SocketTimeoutException e) {
            // hadle timeout
            return "SocketTimeoutException";
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } catch (Exception e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    public static String requestPostQueryUrl(String url, JSONObject data, Boolean isRegistrationActivity) {
        HttpURLConnection urlConnection = null;
        String getParameters = "";
        try {
            // Build Query Data
            //JSONObject json_data = data.getJSONObject("data");

            Iterator<String> jsonIter = data.keys();
            while (jsonIter.hasNext()) {
                String key = jsonIter.next();
                try {
                    String value = data.getString(key);
                    value = URLEncoder.encode(value, "UTF-8");
                    if (getParameters.isEmpty())
                        getParameters = getParameters + key + "=" + value;
                    else
                        getParameters = getParameters + "&" + key + "=" + value;
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }

            String getUrl = url;
            if (!getParameters.isEmpty())
                getUrl = url + "?" + getParameters;

            Log.e("+++++++ PostQuery URL:", " " + getUrl);
            //     String newUrl = URLEncoder.encode(getUrl);
            // create connection

            // String newUrl = URLEncoder.encode(getUrl, "UTF-8");
            URL urlToRequest = new URL(getUrl);
            // Log.e("+++++++++++New Get URL:", " " + newUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();

            urlConnection.setRequestMethod("POST");
            //   urlConnection.setRequestProperty("Accept", "application/json");
            //   urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConnection.setDoOutput(true);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("+++++++++++statusCode:", " " + statusCode);
                // throw some exception
                return "server problem";
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            // handle invalid URL
            return "MalformedURLException";
        } catch (SocketTimeoutException e) {
            // hadle timeout
            return "SocketTimeoutException";
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } catch (Exception e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }


    public static String requestGetUrl(String url, JSONObject data, Boolean isRegistrationActivity) {
        HttpURLConnection urlConnection = null;
        String getParameters = "";
        try {
            // Build Query Data
            //JSONObject json_data = data.getJSONObject("data");

            Iterator<String> jsonIter = data.keys();
            while (jsonIter.hasNext()) {
                String key = jsonIter.next();
                try {
                    String value = data.getString(key);
                    value = URLEncoder.encode(value, "UTF-8");
                    if (getParameters.isEmpty())
                        getParameters = getParameters + key + "=" + value;
                    else
                        getParameters = getParameters + "&" + key + "=" + value;
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }

            String getUrl = url;
            if (!getParameters.isEmpty())
                getUrl = url + "?" + getParameters;

            Log.e("+++++++++++Get URL:", " " + getUrl);
            //     String newUrl = URLEncoder.encode(getUrl);
            // create connection

            // String newUrl = URLEncoder.encode(getUrl, "UTF-8");
            URL urlToRequest = new URL(getUrl);
            // Log.e("+++++++++++New Get URL:", " " + newUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();

          /*  urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConnection.setDoOutput(true);*/

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("+++++++++++statusCode:", " " + statusCode);
                // throw some exception
                return "server problem";
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            // handle invalid URL
            return "MalformedURLException";
        } catch (SocketTimeoutException e) {
            // hadle timeout
            return "SocketTimeoutException";
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } catch (Exception e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }


    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String requestPostUploadImageUrl(String url, JSONObject data, Bitmap image, String fileName, String filefield) {
        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        DataOutputStream outputStream;
        HttpURLConnection urlConnection = null;
        String getParameters = "";

        try {
            // Build Query Data
            JSONObject json_data = data.getJSONObject("data");

            Iterator<String> jsonIter = json_data.keys();
            while (jsonIter.hasNext()) {
                String key = jsonIter.next();
                try {
                    String value = json_data.getString(key);
                    value = URLEncoder.encode(value, "UTF-8");
                    if (getParameters.isEmpty())
                        getParameters = getParameters + key + "=" + value;
                    else
                        getParameters = getParameters + "&" + key + "=" + value;
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }

            String getUrl = url;
            if (!getParameters.isEmpty())
                getUrl = url + "?" + getParameters;

            Log.e("+++++++ PostQuery URL:", " " + getUrl);

            URL requestUrl = new URL(getUrl);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + fileName + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: image/jpeg" + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
            outputStream.writeBytes(lineEnd);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
            byte[] byte_arr = stream.toByteArray();

            outputStream.write(byte_arr, 0, byte_arr.length);

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            String[] posts = getParameters.split("&");
            int max = posts.length;
            for (int i = 0; i < max; i++) {
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                String[] kv = posts[i].split("=");
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + kv[0] + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(kv[1]);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.e("+++++++++++statusCode:", " " + statusCode);
                // throw some exception
                return "server problem";
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            // handle invalid URL
            return "MalformedURLException";
        } catch (SocketTimeoutException e) {
            // hadle timeout
            return "SocketTimeoutException";
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } catch (Exception e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}

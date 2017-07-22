package com.zpc.common.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.zpc.common.exception.ServiceException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
public class HttpUtils {

    static Logger logger = LoggerFactory.getLogger("http");

    private static final Integer SOCKET_TIMEOUT = 59000;

    /**
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     * @throws ServiceException
     * @Description:
     */
    public static JSONObject getUrl(String url) throws HttpException, IOException, ServiceException {
        URL tempUrl = new URL(url);
        HttpClient client = new HttpClient();
        logger.info("{}", url);
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(SOCKET_TIMEOUT);
        client.getHttpConnectionManager().getParams().setSoTimeout(SOCKET_TIMEOUT);
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        logger.info("{}", method.getResponseBodyAsString());
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            return JSONObject.parseObject(method.getResponseBodyAsString());
        } else {
            throw new HttpException("http status is " + method.getStatusCode());
        }

    }

    /**
     * post 请求
     *
     * @param url
     * @param map
     * @return
     * @throws ServiceException
     * @throws IOException
     * @throws HttpException
     * @Description:
     */
    public static JSONObject postUrl(String url, Map<String, String> map) throws ServiceException, HttpException,
        IOException {
        URL tempUrl = new URL(url);
        HttpClient client = new HttpClient();
        logger.info("{}:{}", url, map);
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(SOCKET_TIMEOUT);
        client.getHttpConnectionManager().getParams().setSoTimeout(SOCKET_TIMEOUT);
        PostMethod method = new PostMethod(url);
        if (map != null && map.size() != 0) {
            for (String key : map.keySet()) {
                method.addParameter(key, map.get(key));
            }
        }
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
        client.executeMethod(method);
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            JSONObject result = JSONObject.parseObject(method.getResponseBodyAsString());
            logger.info("{},{}", method.getStatusCode(), result);
            return result;
        } else {
            logger.error(url + ":" + method.getResponseBodyAsString());
            throw new ServiceException("http status is " + method.getStatusCode());
        }

    }
}

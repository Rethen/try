package com.then.atry.domain.net.ex;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by 42524 on 2016/1/28.
 */
public  class ProgressResponseBody extends ResponseBody {


    private final ResponseBody responseBody;
    private final ProgressListener progressListener;
    private BufferedSource bufferedSource;

    private Object tag;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener,Object tag) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
        this.tag=tag;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if(progressListener!=null) {
                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1,tag);
                }
                return bytesRead;
            }
        };
    }

  public   interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done, Object tag);
    }

}

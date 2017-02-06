package com.then.atry.domain.net.ex;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {

    protected RequestBody mDelegate;
    protected Listener mListener;
    protected CountingSink mCountingSink;
    protected String progressKey;

    public ProgressRequestBody(RequestBody delegate, Listener listener,String progressKey) {
        mDelegate = delegate;
        mListener = listener;
        this.progressKey=progressKey;
    }

    @Override
    public MediaType contentType() {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        mCountingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(mCountingSink);
        mDelegate.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {
        private long bytesWritten = 0;
        public CountingSink(Sink delegate) {
            super(delegate);
        }
        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            mListener.onProgress(bytesWritten,contentLength(),(int) (100F * bytesWritten / contentLength()),progressKey);
        }
    }

    public interface Listener {
        void onProgress(long bytesWritten, long contentLength, int progress, String progressKey);
    }
}
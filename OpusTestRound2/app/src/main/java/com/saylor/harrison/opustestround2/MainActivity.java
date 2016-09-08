package com.saylor.harrison.opustestround2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.saylor.harrison.opustestround2.audio.IChunkUploader;
import com.saylor.harrison.opustestround2.audio.OggOpusEnc;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.where_is_the_pool);
    //mediaPlayer.start(); // no need to call prepare(); create() does that for you
    OggOpusEnc og = new OggOpusEnc();
    try {
      InputStream stream = getResources().openRawResource(R.raw.where_is_the_pool);
      byte[] array = IOUtils.toByteArray(stream);
      og.initEncoderWithUploader(new IChunkUploader() {
        @Override public int onHasData(byte[] buffer) {
          return 0;
        }

        @Override public boolean isUploadPrepared() {
          return false;
        }

        @Override public void upload(byte[] data) {

        }

        @Override public void stop() {

        }

        @Override public void prepare() {

        }

        @Override public void setDelegate(ISpeechDelegate delegate) {

        }

        @Override public void close() {

        }
      });
      og.encodeAndWrite(array);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

package padula.delaiglesia.dam.isi.frsf.lab05parte1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.navegador);
        myWebView.loadUrl("file:///android_asset/index.html");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new TareaDao(this), "tareaDaoAndroid");
    }
}

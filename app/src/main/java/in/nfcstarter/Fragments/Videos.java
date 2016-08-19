package in.nfcstarter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.nfcstarter.DescriptionActivity;
import in.nfcstarter.LandingActivity;
import in.nfcstarter.R;

/**
 * Created by Brekkishhh on 19-08-2016.
 */
public class Videos extends Fragment {


    private WebView youTubeVideo;
    private String videoUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos,container,false);

        switch (DescriptionActivity.actualQuery){
            case "Refrigerator":
                videoUrl = "https://www.youtube.com/embed/h5wQoA15OnQ";
                break;
            case "Laptop":
                videoUrl = "https://www.youtube.com/embed/6iIfhgNrhIU";
                break;
            case "Television":
                videoUrl = "https://www.youtube.com/embed/vsFqdPYzsjo";
                break;

        }
        String frameVideo = "<html><body><iframe width=\"320\" height=\"500\" src="+ videoUrl +" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        youTubeVideo = (WebView) view.findViewById(R.id.videoWebView);
        youTubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = youTubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        youTubeVideo.loadData(frameVideo, "text/html", "utf-8");

        return view;
    }
}

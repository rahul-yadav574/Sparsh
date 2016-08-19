package in.nfcstarter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import in.nfcstarter.DescriptionActivity;
import in.nfcstarter.LandingActivity;
import in.nfcstarter.R;

/**
 * Created by Brekkishhh on 19-08-2016.
 */
public class Images extends Fragment {


    private ImageView productImage1;
    private ImageView productImage2;
    private ImageView  productImage3;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images,container,false);

        productImage1 = (ImageView) view.findViewById(R.id.productImage1);
        productImage2 = (ImageView) view.findViewById(R.id.productImage2);
        productImage3 = (ImageView) view.findViewById(R.id.productImage3);

        switch (DescriptionActivity.actualQuery) {

            case "Refrigerator":
                productImage1.setImageDrawable(getResources().getDrawable(R.drawable.refri_1));
                productImage2.setImageDrawable(getResources().getDrawable(R.drawable.refri_2));
                productImage3.setImageDrawable(getResources().getDrawable(R.drawable.refri_3));
                break;
            case "Laptop":
                productImage1.setImageDrawable(getResources().getDrawable(R.drawable.lap_1));
                productImage2.setImageDrawable(getResources().getDrawable(R.drawable.lap_2));
                productImage3.setImageDrawable(getResources().getDrawable(R.drawable.lap_3));
                break;
            case "Television":
                productImage1.setImageDrawable(getResources().getDrawable(R.drawable.tv_1));
                productImage2.setImageDrawable(getResources().getDrawable(R.drawable.tv_2));
                productImage3.setImageDrawable(getResources().getDrawable(R.drawable.tv_3));
                break;
        }

        return view;
    }

}

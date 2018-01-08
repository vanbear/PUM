package pl.wroc.uni.ift.android.quizactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import static pl.wroc.uni.ift.android.quizactivity.R.styleable.MenuItem;

public class SummaryActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView mImageView;
    Button mShareButton;
    Button mPhotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final Globals globals = Globals.getInstance();

        // text
        String pointsString = globals.getCorrectAnswersCount() + " / " + globals.getQuestionsCount();
        TextView pointsTextView = (TextView)findViewById(R.id.pointsSummary);
        pointsTextView.setText(pointsString);

        TextView tokensTextView = (TextView)findViewById(R.id.tokensSummary);
        tokensTextView.setText(globals.getCheatTokensCount()+"");


        // share button
        mShareButton = (Button)findViewById(R.id.summary_share_button);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Uojezu! Zdobyłem "+globals.getCorrectAnswersCount()+" na "+globals.getQuestionsCount()+" punktów w GeoQuiz! I do tego zostało mi "+globals.getCheatTokensCount()+" oszukaństw. Przebij to, lamusie 8)";

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        mPhotoButton = (Button)findViewById(R.id.summary_photo_button);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeAPhotoIntent();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Android Camera application encodes photho in return Intent delivered to
            // onActivityResult as small Bitmap in the extras under the key data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView = (ImageView)findViewById(R.id.image_view);
            Log.d("Camera","Before");
            if (imageBitmap!=null)
                mImageView.setImageBitmap(imageBitmap);
            Log.d("Camera","After");

            // This image is thumbnail the full size file is stored in internal camera files;
        }
        //TODO: Configure file provider to store full size photos
    }

    private void takeAPhotoIntent() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }

    }


}




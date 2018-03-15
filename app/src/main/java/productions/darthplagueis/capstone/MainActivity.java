package productions.darthplagueis.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableException;

/**
 * Temporary activity used for testing different SDK versions.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checks to see if the current device is capable of using ARCore.
        try {
            switch (ArCoreApk.getInstance().checkAvailability(this)) {
                case UNSUPPORTED_DEVICE_NOT_CAPABLE:
                    Toast.makeText(this, "Unsupported device.", Toast.LENGTH_SHORT).show();
                    break;
                case SUPPORTED_NOT_INSTALLED:
                    setButton();
                    break;
                case SUPPORTED_INSTALLED:
                    setButton();
                    break;
            }
        } catch (UnavailableException e) {
            Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setButton() {
        findViewById(R.id.ar_button).setVisibility(View.VISIBLE);
        findViewById(R.id.ar_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArExperienceActivity.class));
            }
        });
    }
}

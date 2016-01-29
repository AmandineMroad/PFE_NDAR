package polytech.pfe_ndar;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by Oirled on 29/01/2016.
 */
public class NfcActivity extends AppCompatActivity {
    NfcAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    mAdapter=NfcAdapter.getDefaultAdapter(this);
    resolveIntent(getIntent());
}

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }
    void resolveIntent(Intent intent)
    {
        Log.d("test", "niqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUEniqueNIQUE");

        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages;
            if (rawMsgs != null) {
                messages = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    messages[i] = (NdefMessage) rawMsgs[i];
                    NdefRecord record = messages[i].getRecords()[i];
                    byte[] id = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();

                    String message = getTextData(record.getPayload());
                }
            }
        } else {
            finish();
            return;
        }
    }

    String getTextData(byte[] payload) {
        String texteCode = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langageCodeTaille = payload[0] & 0077;
        try
        {
            return new String(payload, langageCodeTaille + 1, payload.length - langageCodeTaille - 1, texteCode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
    protected void onResume(){
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

}

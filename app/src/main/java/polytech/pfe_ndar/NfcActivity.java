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
    byte currentId;
    public void onCreate(Bundle savedInstanceState){
        Log.d("NFC READINGS","NFCACTIVITY CREATED");
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

        String action = intent.getAction();
        Log.d("NFC READINGS", "Action recupereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            byte[] idmain = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
          //  Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_ID);
            currentId=idmain[0];
            NdefMessage[] messages;
            Log.d("NFC READINGS", "action tostring=" + action+ " ID="+idmain);

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

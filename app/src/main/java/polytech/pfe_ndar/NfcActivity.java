package polytech.pfe_ndar;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import polytech.pfe_ndar.util.PopUp;

/**
 * Created by Oirled on 29/01/2016.
 */
public class NfcActivity extends AppCompatActivity {
    NfcAdapter mAdapter;
    String currentId;
    private PopUp popup;
    public void onCreate(Bundle savedInstanceState){

        Log.d("NFC READINGS","NFCACTIVITY CREATED");
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc2);
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
           Tag idmain = intent.getParcelableExtra((NfcAdapter.EXTRA_TAG)) ;
          //  Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_ID);
          //  currentId=idmain.clone();
            NdefMessage[] messages;
            currentId=convertByteArrayToHexString(idmain.getId());
            Log.d("NFC READINGS", "action tostring=" + action + " ID=" +currentId );

        } else {
            finish();
            return;
        }
        TextView textView = (TextView) findViewById(R.id.tagid);
        textView.setText("id= "+currentId);

        for (polytech.pfe_ndar.object.Tag tagcurr : polytech.pfe_ndar.object.Tag.tagSet ) {
            if(tagcurr.tagID.equals(currentId)){
                Log.d("OKPOPUP", "FOUNDMATCH"+currentId+"\n Flag : "+ tagcurr.tagFlag.getPiece().getName());
                popup=PopUp.newInstance(tagcurr.tagFlag, this);
                popup.show(this.getFragmentManager(),"");
            }
        }


    }


    public static String convertByteArrayToHexString (byte[] b) {
        if (b != null) {
            StringBuilder s = new StringBuilder(2 * b.length);
            for (int i = 0; i < b.length; ++i) {
                final String t = Integer.toHexString(b[i]);
                final int l = t.length();
                if (l > 2) {
                    s.append(t.substring(l - 2));
                } else {
                    if (l == 1) {
                        s.append("0");
                    }
                    s.append(t);
                }
            }
            return s.toString();
        } else {
            return "";
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

package com.example.ryu.permissiontest;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mResult;
    final int READ_CONTACT_CODE =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult=(TextView) findViewById(R.id.result);
    }

    public void mOnClick(View v){               //클릭시 상황을 케이스별로 처리
        switch (v.getId()){
            case R.id.btnread:
//                outContact();
                tryOutContact();
                break;
            case R.id.btnreset:
                mResult.setText("주소록");
                break;
        }
    }

    void tryOutContact(){               //권한 설정 상태를 통해 경우를 나눔
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){ //이미 허가 되어있음
            Toast.makeText(this, "허가된 상태", Toast.LENGTH_SHORT).show();
            outContact();
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){    //권한이 없을때 TRUE 리턴
                //권한을 이미 얻었다면 설명을 위한 대화상자를 띄우지 않아도 된다.
                new AlertDialog.Builder(this)                           //권한이 없을때 띄울 알림창을 설정한다
                        .setMessage("이 프로그램이 원활하게 동작하기 위해서는 "+
                        "주소록 읽기 권한이 꼭 필요합니다")
                        .setTitle("Permission Denied")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {            //YES 클릭시 권한을 요청한다.
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACT_CODE);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                //NO를 클릭하면 알림창을 보고 권한을 제공할 용의가 없으므로 권한 요청을 취소한다
                    }
                }).show();
            }else{
                //이 부분은 사용자가 권한요청을 다시는 묻지 않겠다고 Never ask me 라는 것을 체크했을때 이부분으로 들어오게 된다.
                Toast.makeText(this,"허가된 상태가 아니어서 권한 요청",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACT_CODE);
            }
        }
    }

    @Override
    //권한 요청에 따른 결과를 출력해주는 기능이다.
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        //요청 코드에 따라서 케이스별로 처리 , 이 경우에는 예제 코드이기때문에 한가지 케이스밖에 없다
        switch (requestCode){
            case READ_CONTACT_CODE:             //읽기 요청 권한이 들어왔을때 확인을 하고 토스트로 띄워준 뒤에 요청으로 들어간다.
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                    outContact();
                }else{
                    //토스트로 거절되었다고 띄워준다.
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
        }
    }

    //권한 요청을 받은 후에 주소록에서 실제로 정보를 받아와서 띄워주는 부분이다
    void outContact(){
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        int name_idx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        if(cursor.moveToNext()){
            //다음 것으로 옮겼을 때 커서가 가리키는 것이 있다면 출력
            mResult.setText(cursor.getString(name_idx));
        }
        else{
            //없다면 주소록이 비어있다고 출력
            mResult.setText("Contact is empty.");
        }
        cursor.close();
    }
}

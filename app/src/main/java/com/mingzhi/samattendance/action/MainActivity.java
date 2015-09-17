//package com.mingzhi.samattendance.action;
//
//import android.support.v7.app.ActionBarActivity;
//import android.os.Bundle;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.internal.bind.DateTypeAdapter;
//import com.mingzhi.samattendance.action.framework.Crackd;
//import com.mingzhi.samattendance.action.framework.RecievCC;
//import com.mingzhi.samattendance.action.framework.TransIntoAccoutAuditModel;
//import com.mingzhi.samattendance.action.framework.XXBAPI;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Date;
//
//import retrofit.Callback;
//import retrofit.RestAdapter;
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//import retrofit.converter.GsonConverter;
//import retrofit.mime.TypedByteArray;
//import retrofit.mime.TypedInput;
//
//
//public class MainActivity extends ActionBarActivity {
//
//    Button search_button;
//    long cur,tmp;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//       final TransIntoAccoutAuditModel model =new TransIntoAccoutAuditModel();
//        model.setImei("0000000000");
//        model.setNum("chenjianhui");
//        model.setPassword("753789");
//        model.setVersions("4.2.1");
//        search_button=(Button)findViewById(R.id.search_button);
//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Gson gson = new GsonBuilder()
//                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                        .registerTypeAdapter(Date.class, new DateTypeAdapter())
//                        .create();
//                RestAdapter restAdapter =new RestAdapter.Builder().setConverter(new GsonConverter(gson)).setEndpoint(XXBAPI.URL).build();
//                XXBAPI api=restAdapter.create(XXBAPI.class );
//
//                cur= System.currentTimeMillis();
//                TypedInput in =new TypedByteArray("application/json",gson.toJson(model).getBytes());
//                api.listTrasn(in, new Callback<XXBAPI.User>() {
//                    @Override
//                    public void success(User user, Response response) {
//
//                        tmp=System.currentTimeMillis();
//                        Toast.makeText(MainActivity.this,"SUCCESS! "+( tmp-cur),Toast.LENGTH_LONG ).show();
//                    }
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Toast.makeText(MainActivity.this,"error!",Toast.LENGTH_SHORT ).show();
//                    }
//                });
//
////                "areaname":"雁塔区","cityname":"西安市","contactId":"b3272ddd8cd54c1f9c1a51e26b5e64de","coordinate":"34.200847,108.894025","crackedLocation":"陕西省西安市雁塔区锦业路1号","filePath1":"/9j
//
////                "areaname":"雁塔区",
////                        "cityname":"西安市",
////                        "contactId":"a7437914361446fdb9d6ac7dc6867fd5",
////                        "coordinate":"34.20094,108.894016",
////                        "crackedLocation":"陕西省西安市雁塔区锦业路1号",
////                        "flag":"9",
////                        "kiId":"8dca77f54e0648119167e9a29596dbee",
////                        "kiName":"西安印刷厂刘同",
////                        "provincename":"陕西省",
////                        "saasFlag":"035655aac00147b2a81ee3111b575f7a",
////                        "userId":"0e91808725b14c8ea403bcc05bdee788",
////                        "workContent":" nmmjj"
////                Crackd  crackd=new Crackd();
////                crackd.setAreaname("雁塔区");
////                crackd.setCityname("西安市");
////                crackd.setContactId("b3272ddd8cd54c1f9c1a51e26b5e64de");
////                crackd.setCoordinate("34.200847,108.894025");
////                crackd.setCrackedLocation("陕西省西安市雁塔区锦业路1号");
////                crackd.setFilePath1(getFileByte("/storage/emulated/0/DCIM/Camera/20150801_134020.jpg"));
////                crackd.setFlag("9");
////                crackd.setKiId("8dca77f54e0648119167e9a29596dbee");
////                crackd.setKiName("西安印刷厂刘同");
////                crackd.setProvincename("陕西省");
////                crackd.setSaasFlag("035655aac00147b2a81ee3111b575f7a");
////                crackd.setUserId("0e91808725b14c8ea403bcc05bdee788");
////                crackd.setWorkContent(" nmmjj");
////                TypedInput in =new TypedByteArray("application/json",gson.toJson(crackd).getBytes());
////                api.insertC(in, new Callback<RecievCC>() {
////                    @Override
////                    public void success(RecievCC user, Response response) {
////
////                        tmp = System.currentTimeMillis();
////                        Toast.makeText(MainActivity.this, "SUCCESS! " + (tmp - cur), Toast.LENGTH_LONG).show();
////                    }
////
////                    @Override
////                    public void failure(RetrofitError error) {
////                        Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
////                    }
////                });
////
////            }
////        });
//
//    }
////    public static String getFileByte(String filePath) {
////        int count;
////        int num = 0;
////        File file = new File(filePath);
////        long len = file.length();
////        if (file.exists()) {
////            FileInputStream fis = null;
////            StringBuffer sb = new StringBuffer();
////            try {
////                fis = new FileInputStream(file);
////                byte[] buffer = new byte[(int) len];
////                while ((count = fis.read(buffer)) != -1) {
////                    sb.append(Base64.encodeToString(buffer, Base64.DEFAULT));
////                    num = count++;
////                }
////                return sb.toString();
////            } catch (Exception e) {
////                e.printStackTrace();
////            } finally {
////                try {
////                    fis.close();
////                } catch (IOException e) {
////
////                    e.printStackTrace();
////                }
////            }
////        }
////        return "";
////    }
//
//}

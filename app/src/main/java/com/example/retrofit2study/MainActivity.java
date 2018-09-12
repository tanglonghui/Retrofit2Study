package com.example.retrofit2study;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofit2study.model.User;
import com.example.retrofit2study.net.Api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button10 = (Button) findViewById(R.id.button10);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Api.getUserService().getUser("1433227").enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.button1:
                Api.getUserService().getUserByPost("1433227").enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button2:
                Api.getUserService().getUsersBySort("test").enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button3:
                Api.getUserService().postUserByJson(new User("1", "1"))
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.button4:
                Api.getUserService().login("1", "1").enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button5:
                //  checkPermission();
                File imageFile = new File(getExternalFilesDir(""), "cxy.txt");
                Log.d("地址:", "" + imageFile);
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("picture", imageFile.getName(), requestFile);

                // add another part within the multipart request
                String descriptionString = "hello, this is description speaking";
                RequestBody description =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), descriptionString);
                Api.getUserService().testSingleMultipart(description, body)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(MainActivity.this, "结果" + response.body(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "结果失败", Toast.LENGTH_SHORT).show();

                            }
                        });
                break;
            case R.id.button6:
                File file = new File(getExternalFilesDirs("test") + "test.txt");
                // RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/jpeg"),file);
                RequestBody text = RequestBody.create(MediaType.parse("multipart/form-data"), new File(getExternalFilesDir(""), "cxy.txt"));
                RequestBody p1 = RequestBody.create(MediaType.parse("multipart/form-data"), new File(getExternalFilesDir(""), "test.jpg"));
                RequestBody p2 = RequestBody.create(MediaType.parse("multipart/form-data"), new File(getExternalFilesDir(""), "test1.jpg"));
                RequestBody password = RequestBody.create(MediaType.parse("text/plan"), "123456");
                Map<String, RequestBody> photos = new HashMap<String, RequestBody>();
                photos.put("photos\";filename=\"test0.jpg", p1);
                photos.put("tests\";filename=\"text.jpg", p2);
                photos.put("tests\";filename=\"cxy.txt", text);
                Api.getUserService().upload(photos, password).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case R.id.button7:

                Api.getUserService().downloadTest().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        final InputStream inputStream = response.body().byteStream();
                        try {

                            Log.d("ttt", "" + getExternalFilesDir("") + "/test.txt");
                            final FileOutputStream outputStream = new FileOutputStream(getExternalFilesDir("download") + "/download.txt");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    int len;
                                    try {
                                        while ((len = inputStream.read()) != -1) {
                                            outputStream.write(len);
                                            // Log.d("sj", "run: "+len);
                                        }
                                        inputStream.close();
                                        outputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
            case R.id.button8:
                file = new File(getExternalFilesDir(""), "cxy.txt");
                File file1 = new File(getExternalFilesDir(""), "test.jpg");
                try {

                    //File file =new File(Environment.getExternalStorageDirectory(),"test/cxy.txt");
                    //自己专属文件夹的路径

                    //File file =new File("/mnt/sdcard/cxy.txt");
                    //File file = ;

                    String content = "今天天气不错";
                    file.createNewFile();
                    file1.createNewFile();
                    //实例化一个输出流
                    FileOutputStream out = new FileOutputStream(file);
                    //FileOutputStream out=new FileOutputStream("/mnt/sdcard/cxy.txt");
                    //把文字转化为字节数组
                    byte[] bytes = content.getBytes();
                    //写入字节数组到文件
                    out.write(bytes);
                    //关闭输入流
                    out.close();
                    Log.d("地址:", "" + file);
                    Toast.makeText(MainActivity.this, "写入成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.button9:


                //获取/data/data/package/files
                file = getFilesDir();
                //获取/data/data/package/cache
                File file2 = getCacheDir();
                //获取/data/data/package/下的指定名称的文件夹File对象，如果该文件夹不存在则用指定名称创建一个新的文件夹。
                File file3 = getDir("com.example.administrator.downloaddemo", MODE_PRIVATE);
                //获取/data/data/package/files下所有的文件名
                String[] fileList = fileList();
                //删除/data/data/package/files/test文件
                String name = "test";
                deleteFile(name);
                //获取/storage/emulated/0/Android/data/pacakge/cache
                File file4 = getExternalCacheDir();
                //获取SD路径 /storage/emulated/0
                File file5 = Environment.getExternalStorageDirectory();
                //在SD路径创建文件夹
                String path = file5.getPath() + "/mkdirDemo";
                File mkdir = new File(path);
                mkdir.mkdir();

                if (mkdir.exists()) {
                    Log.d("Tag", "createNewFile: " + "redfinger.log");
                    //在path下创建文件
                    try {
                        File mfile = new File(path + "/redfinger.log");
                        mfile.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Log.d("Tag", "file: " + file.toString());
                Log.d("Tag", "file2: " + file2.toString());
                Log.d("Tag", "file3: " + file3.toString());
                Log.d("Tag", "file4: " + file4.toString());
                Log.d("Tag", "file5: " + file5.toString());
                break;
            case R.id.button10:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

                break;
        }
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("sssssssssssss", "checkPermission: 已经授权！");
        }
    }
}

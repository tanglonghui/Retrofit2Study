package com.example.retrofit2study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofit2study.model.User;
import com.example.retrofit2study.net.Api;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button buttonInit = (Button) findViewById(R.id.buttoninit);
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

        buttonInit.setOnClickListener(this);
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
            case R.id.buttoninit:
                //两个一拆开就好理解了
                ObservableOnSubscribe<Integer> s = new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                        e.onComplete(); //结束
                        e.onNext(4);
                    }
                };
                Observer<Integer> b = new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("zhao", "onSubscribe: " + d.isDisposed());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.e("zhao", "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("zhao", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("zhao", "onComplete: ");
                    }
                };
                Observable.create(s).subscribe(b);
                break;
            case R.id.button:
                //正确返回依次走 124  错误直接进3
                Api.RxjavaGetService().getUser("1433223")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<User>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                               /*
                               吃了英语的亏，大概，感觉是推荐我们用这个值吧
                               大概是设置背压什么的
                                */
                                s.request(Long.MAX_VALUE);
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(User user) {
                                //在这里进行返回消息出来
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable t) {
                                //错误走这里
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.button1:
                //正确返回依次走 124  错误直接进3
                Api.RxjavaGetService().getUserByPost("1433223")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.button2:
                Api.RxjavaGetService().getUsersBySort("test")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.button3:
                Api.RxjavaGetService().postUserByJson(new User("1", "1"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
                            }
                        });

                break;
            case R.id.button4:
                Api.RxjavaGetService().login("1", "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
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
                Api.RxjavaGetService().testSingleMultipart(description, body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
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
                Api.RxjavaGetService().upload(photos, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull User user) {
                                Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(Main2Activity.this, "3", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(Main2Activity.this, "4", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.button7:
                Api.RxjavaGetService().downloadTest()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull ResponseBody responseBody) {
                                final InputStream inputStream = responseBody.byteStream();
                                Toast.makeText(Main2Activity.this, "文件下载成功", Toast.LENGTH_SHORT).show();
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
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            case R.id.button10:
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

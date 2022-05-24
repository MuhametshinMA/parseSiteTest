package com.example.parsesitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView lvCourses;
    private CustomArrayAdapter adapter;
    private List<Currencies> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        lvCourses = findViewById(R.id.lvCourses);
        arrList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_item_1, getLayoutInflater(), arrList);
        lvCourses.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
    private  void getWeb() {
        try {
            //Connection connection = Jsoup.connect("https://tumen.bankiros.ru/currency");
            //connection.userAgent("Mozilla/5.0");
            InputStream filename = getAssets().open("Currency.html");
            //File input = new File(filename);
            doc = Jsoup.parse(filename, "UTF-8", "http://example.com/");

            //doc = Jsoup.connect("https://tumen.bankiros.ru/currency")
            //        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53")
            //        .ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true)
            //        .get();
            Elements tables = doc.getElementsByTag("tbody");

            Element tableFirst = tables.get(0);
            Elements elemTables = tableFirst.children();

            Log.d("MyLog", "1: " + elemTables.size());
            for (int i = 2; i < elemTables.size(); i++) {
                Element currencies = elemTables.get(i);
                Elements elemFirstCurr = currencies.children();
                Log.d("MyLog", i + ": " + elemTables.get(i).text());
                Elements currName = elemFirstCurr.get(0).getElementsByTag("a");
                Log.d("MyLog","currency: " + currName.text());
                Elements courseByBankFirst = elemFirstCurr.get(1).getElementsByTag("span");
                Log.d("MyLog","course: " + courseByBankFirst.text());
                Elements bankFirst = elemFirstCurr.get(1).getElementsByTag("a");
                Log.d("MyLog","bank: " + bankFirst.text());
                Elements courseByBankSec = elemFirstCurr.get(2).getElementsByTag("span");
                Log.d("MyLog","course: " + courseByBankFirst.text());
                Elements bankSec = elemFirstCurr.get(2).getElementsByTag("a");
                Log.d("MyLog","bank: " + bankFirst.text());
                Currencies currency = new Currencies(currName.text(),
                        bankFirst.text(), courseByBankFirst.text(),
                        bankSec.text(), courseByBankSec.text());
                arrList.add(currency);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
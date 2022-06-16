package com.example.imagepro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class searchActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //bottomnavigationview 출력
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.searchItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeItem:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.searchItem:
                        return true;

                    case R.id.cameraItem:
                        startActivity(new Intent(getApplicationContext()
                                , opencvActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.calendarItem:
                        startActivity(new Intent(getApplicationContext()
                                , calendarActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });

        //데이터 연결
        /*searchView = findViewById(R.id.searchView);
        readdata = (TextView) findViewById(R.id.readTV);
        contList = new ArrayList<>(); // 리스트를 생성
        contList.clear();
        dbHelper = new SqlLiteDbHelper(this);
        dbHelper.openDataBase();
        contList = dbHelper.getDetails();

        String msg = "";
        for (int i = 0; i < contList.size(); i++) {

            DBhelper cont = contList.get(i);
            msg = msg + cont.getId() + " " + cont.getName() + " " + cont.getAmount() + " " + cont.getEnergy() + " " + cont.getSugar() + " " + cont.getSodium() + "\n\n\n";
            readdata.setText(msg);
        }*/

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        listTitle = new ArrayList<String>();
        settingBookList();


        arraylistTitle = new ArrayList<String>();
        arraylistTitle.addAll(listTitle);

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

    }



    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
// arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylistTitle.get(i).toLowerCase().contains(charText))
                {
// 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.

    // book list
    List<FoodList> mBookList = new ArrayList<FoodList>();
    private List<String> listTitle;
    private ArrayList<String> arraylistTitle;


    // book list class
    public class FoodList {
        public String foodname;
        public String amount;
        public int energy;
        public int sugar;
        public int sodium;
    }

    // setting book list
    private void settingBookList(){
        FoodList food = new FoodList();

        food.foodname = "가나슈생크림 조각케이크";
        food.amount = "100";
        food.energy = 330;
        food.sugar = 20;
        food.sodium = 80;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가나슈쿠키";
        food.amount = "140";
        food.energy = 776;
        food.sugar = 21;
        food.sodium = 11;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가래떡";
        food.amount = "100";
        food.energy = 195;
        food.sugar = 0;
        food.sodium = 240;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가오리찜";
        food.amount = "200";
        food.energy = 204;
        food.sugar = 0;
        food.sodium = 302;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가오리회무침";
        food.amount = "230";
        food.energy = 186;
        food.sugar = 12;
        food.sodium = 813;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가자미구이";
        food.amount = "200";
        food.energy = 245;
        food.sugar = 0;
        food.sodium = 630;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가자미두마리조림";
        food.amount = "100";
        food.energy = 99;
        food.sugar = 3;
        food.sodium = 378;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가자미식혜";
        food.amount = "250";
        food.energy = 335;
        food.sugar = 9;
        food.sodium = 1711;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가자미전";
        food.amount = "150";
        food.energy = 230;
        food.sugar = 0;
        food.sodium = 819;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가자미조림";
        food.amount = "300";
        food.energy = 297;
        food.sugar = 10;
        food.sodium = 1692;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "가지전";
        food.amount = "200";
        food.energy = 245;
        food.sugar = 1;
        food.sodium = 674;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "간자장";
        food.amount = "650";
        food.energy = 824;
        food.sugar = 12;
        food.sodium = 2716;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "고구마피자";
        food.amount = "150";
        food.energy = 334;
        food.sugar = 20;
        food.sodium = 912;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "김밥";
        food.amount = "230";
        food.energy = 322;
        food.sugar = 0;
        food.sodium = 705;
        mBookList.add(food);

        food = new FoodList();

        food.foodname = "등심돈까스";
        food.amount = "230";
        food.energy = 623;
        food.sugar = 3;
        food.sodium = 574;
        mBookList.add(food);

        food = new FoodList();

        settingSearchList();
    }

    // setting search list
    private void settingSearchList(){
        for(int i = 0; i < mBookList.size(); i++){
            list.add(mBookList.get(i).foodname + " 1회제공량: " + mBookList.get(i).amount + " 에너지: " + mBookList.get(i).energy + " 총당류: " + mBookList.get(i).sugar + " 나트륨: " + mBookList.get(i).sodium);
            listTitle.add(mBookList.get(i).foodname);
        }
    }



}

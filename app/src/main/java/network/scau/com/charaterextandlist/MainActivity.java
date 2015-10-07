package network.scau.com.charaterextandlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CEList listView;
    private EditText editText;
    private Button button;

    private CEAdapter ceAdapter;
    private CharactersGuideView guideView;

    private TextView tv;
    private List<Cell> datas;
    private CEView ceView;

    private List<String> index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIndex();
        initDatas();

        ceView = (CEView) findViewById(R.id.id_ceview);

        ceView.setDatasandIndex(datas, index);
        ceView.setCharecterColor(Color.YELLOW);
        ceView.setContextColor(Color.GRAY);
        ceView.setContextTextColor(Color.BLACK);
        ceView.setCharacterTextColor(Color.BLUE);
        ceView.setNormalGuideViewBackgroundColor(0x33888888);
        ceView.setNormalGuideViewTextColor(Color.WHITE);
        ceView.setSelectedGuideViewBackgroundColor(0x99888888);
        ceView.setSelectedGuideViewTextColor(Color.YELLOW);
        ceView.setAverage(false);
        ceView.setGuideTextSize(60);
        ceView.setIndexBlock(6);



    }


    public void click(View view) {
        String c = editText.getText().toString().substring(0, 2);
        Integer charIndex = ceAdapter.getCharIndex(c);
        if (charIndex != -1) {
            listView.setSelection(charIndex);
        }
    }

    private void initIndex() {
        index = new ArrayList<String>();
//        for (int i = 'A'; i <= 'Z'; i++) {
//            index.add("" + (char) i);
//        }
        index.add("上");
        index.add("东");
        index.add("南");
        index.add("西");
        index.add("北");
        index.add("下");
    }


    private void initDatas() {
        datas = new ArrayList<Cell>();

        char c = 'A';
        String s = "上";
        for (int i = 0; i < 30; i++) {
            Cell cell = new Cell();
            if (i > 5) {
                s = "东";
            }
            if (i > 11) {
                s = "南";
            }
            if (i > 15) {
                s = "西";
            }

            if (i > 20) {
                s = "北";
            }
            if (i > 26) {
                s = "下";
            }

            cell.setCharacter(c);
            cell.setName(s + "逗逼");
            datas.add(cell);
        }

    }


}

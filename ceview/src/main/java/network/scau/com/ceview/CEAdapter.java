package network.scau.com.ceview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhy on 2015/9/24 0024.
 */
public class CEAdapter extends BaseAdapter {
    private List<Cell> datas;
    private Context context;
    private LayoutInflater inflater;
    private Map<String, Integer> strIndex;


    //索引背景色
    private int charecterColor = -1;

    //索引字体颜色
    private int characterTextColor = -1;

    //内容背景色
    private int ccontextColor = -1;

    //内容字体颜色
    private int contextTextColor = -1;

    //    private char currentChar = '@';
    private String currentStr = "";
    private static String TAG = "CEAdapter";

    public CEAdapter(Context context, List<Cell> datas) {
        this.context = context;
        this.datas = datas;
        strIndex = new HashMap<String, Integer>();
        handleDatas();
    }


    public Integer getCharIndex(String c) {
        Integer t = strIndex.get(c);
        if (t != null)
            return t;
        else return -1;
    }

    public void handleDatas() {
        if (datas != null) {
            Cell cell;
            for (int i = 0; i < datas.size(); i++) {
                cell = datas.get(i);
//                char c = cell.getCharacter();
                String str = cell.getFirstStr();
                if (str.equals(currentStr)) {
                    cell.setFirstStr(null);
                } else {
                    currentStr = str;
                    strIndex.put(str, i);
                }
            }
        }
    }


    public void setDatas(List<Cell> datas) {
        this.datas = datas;
        handleDatas();
    }

    public List<Cell> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCcontextColor(int ccontextColor) {
        this.ccontextColor = ccontextColor;
    }

    public void setCharecterColor(int charecterColor) {
        this.charecterColor = charecterColor;
    }

    public void setCharacterTextColor(int characterTextColor) {
        this.characterTextColor = characterTextColor;
    }

    public void setContextTextColor(int contextTextColor) {
        this.contextTextColor = contextTextColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CEListViewHolder holder;
        if (convertView != null) {
            holder = (CEListViewHolder) convertView.getTag();
        } else {
            holder = new CEListViewHolder();
            convertView = View.inflate(context, R.layout.celist_cell, null);
            holder.setContext((TextView) convertView.findViewById(R.id.id_tv_context));
            holder.setCharacter((TextView) convertView.findViewById(R.id.id_tv_character));
            convertView.setTag(holder);
        }
//        char c = datas.get(position).getCharacter();
        if (charecterColor != -1) {
            holder.setCharacterColor(charecterColor);
        }

        if (ccontextColor != -1) {
            holder.setContextColor(ccontextColor);
        }

        if (characterTextColor != -1) {
            holder.setCharacterTextColor(characterTextColor);
        }

        if (contextTextColor != -1) {
            holder.setContextTextColor(contextTextColor);
        }

        String s = datas.get(position).getFirstStr();
        String context = datas.get(position).getName();
        holder.setCharacterText(s);
        holder.setContextText(context);
        if (s == null) {
            holder.setSingle(false);
        } else {
            holder.setSingle(true);
        }
        return convertView;
    }


    class CEListViewHolder {
        private TextView context;
        private TextView character;

        public void setContextText(String text) {
            context.setText(text);
        }

        public void setCharacterText(String text) {
            character.setText(text);
        }

        public void setContext(TextView context) {
            this.context = context;
        }

        public void setCharacter(TextView character) {
            this.character = character;
        }

        public String getContextText() {

            return context.getText().toString();
        }

        public String getCharacterText() {

            return character.getText().toString();
        }

        public void setSingle(boolean visiable) {
            if (visiable) {
                character.setVisibility(View.VISIBLE);
            } else {
                character.setVisibility(View.GONE);
            }

        }

        public void setCharacterColor(int color) {
            character.setBackgroundColor(color);
        }

        public void setCharacterTextColor(int color) {
            character.setTextColor(color);
        }

        public void setContextColor(int color) {
            context.setBackgroundColor(color);
        }

        public void setContextTextColor(int color) {
            context.setTextColor(color);
        }

    }
}

package com.lch.app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lch.netkit.common.base.AbsAdapter;
import com.lch.netkit.common.base.BaseCompatActivity;
import com.lch.netkit.common.tool.VF;

import java.util.Arrays;

public class OnlineMusicListActivity extends BaseCompatActivity {

    private OnlineMusicListActivity thiz;
    private ListView lvMusic;
    private MusicListAdp mMusicListAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thiz = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_music_list);
        lvMusic = f(R.id.lvMusic);
        Toolbar toolbar = f(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nav.openActivity(MusicPathInputActivity.class, thiz);
            }
        });

        mMusicListAdp = new MusicListAdp();
        lvMusic.setAdapter(mMusicListAdp);

        mMusicListAdp.refresh(Arrays.asList("music1", "music2"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_online_music_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private static class MusicListAdp extends AbsAdapter<String> {


        @Override
        public AbsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new MyAbsViewHolder(viewType, viewGroup.getContext());
        }

        @Override
        public void onBindViewHolder(AbsViewHolder absViewHolder, int pos) {
            String data = getItem(pos);
            MyAbsViewHolder vh = (MyAbsViewHolder) absViewHolder;
            vh.tvName.setText(data);
        }

        private static class MyAbsViewHolder extends AbsViewHolder {

            private final View view;
            private final TextView tvName;

            public MyAbsViewHolder(int viewType, Context context) {
                super(viewType);
                view = View.inflate(context, R.layout.item_music_lsit, null);
                tvName = VF.f(view, R.id.tvName);
            }

            @Override
            protected View getItemView() {
                return view;
            }
        }

    }


}

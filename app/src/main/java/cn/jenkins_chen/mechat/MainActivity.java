package cn.jenkins_chen.mechat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import cn.jenkins_chen.mechat.fragment.FragmentContacts;
import cn.jenkins_chen.mechat.fragment.FragmentDiscover;
import cn.jenkins_chen.mechat.fragment.FragmentMe;
import cn.jenkins_chen.mechat.fragment.FragmentWeChat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    String DBG_TAG_LL_LSNER= "nav_lsner";

    LinearLayout ll_wechat = null;
    LinearLayout ll_contacts= null;
    LinearLayout ll_discover = null;
    LinearLayout ll_me = null;

    ImageView img_wechat = null;
    ImageView img_contacts = null;
    ImageView img_discover = null;
    ImageView img_me = null;

    TextView tv_wechat = null;
    TextView tv_contacts = null;
    TextView tv_discover = null;
    TextView tv_me = null;


    Fragment fragmentWechat=null;
    Fragment fragmentContacts= null;
    Fragment fragmentDiscover=null;
    Fragment fragmentMe=null;


    ViewPager viewPager = null;
    WeChatViewPagerAdpter viewPagerAdpter =null;

    List<Fragment> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);

       bindCmpt();
        
        setLsner();

        initFrgmt();
        initPageScroll();

        initStartDisplay();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wechat_menu,menu);
        setIconVisible(menu,true);
        return true;
    }

    //通过反射机制显示overflow中的图标
    public void setIconVisible(Menu menu, boolean visable){
        Field field;
        try {
            field = menu.getClass().getDeclaredField("mOptionalIconsVisible");
            //Log.d(TAG," setIconVisible1() field="+field);
            field.setAccessible(true);
            field.set(menu, visable);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initFrgmt() {
        fragmentWechat = new FragmentWeChat();
        fragmentContacts = new FragmentContacts();
        fragmentDiscover = new FragmentDiscover();
        fragmentMe = new FragmentMe();
    }

    private void initPageScroll() {
        list = new ArrayList<>();
        list.add(fragmentWechat);
        list.add(fragmentContacts);
        list.add(fragmentDiscover);
        list.add(fragmentMe);
        FragmentManager fm = getSupportFragmentManager();
        viewPagerAdpter = new WeChatViewPagerAdpter(fm);
        viewPager.setAdapter(viewPagerAdpter);
        viewPager.setOnPageChangeListener(this);

    }

    private void initStartDisplay() {


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(fragmentWechat==null)
        {
            fragmentWechat=new FragmentWeChat();
            ft.add(R.id.display_page,fragmentWechat);
        }else
        {
            ft.show(fragmentWechat);
        }
        ft.commit();

    }

    private void setLsner() {
        ll_wechat.setOnClickListener(this);
        ll_contacts.setOnClickListener(this);
        ll_discover.setOnClickListener(this);
        ll_me.setOnClickListener(this);

        img_wechat.setOnClickListener(this);
        img_contacts.setOnClickListener(this);
        img_discover.setOnClickListener(this);
        img_me.setOnClickListener(this);

        tv_wechat.setOnClickListener(this);
        tv_contacts.setOnClickListener(this);
        tv_discover.setOnClickListener(this);
        tv_me.setOnClickListener(this);
    }

    private void bindCmpt() {

        ll_wechat= (LinearLayout) findViewById(R.id.ll_nav_wechat);
        ll_discover= (LinearLayout) findViewById(R.id.ll_nav_discover);
        ll_contacts= (LinearLayout) findViewById(R.id.ll_nav_contacts);
        ll_me= (LinearLayout) findViewById(R.id.ll_nav_me);

        img_wechat= (ImageView) findViewById(R.id.img_wechat);
        img_contacts= (ImageView) findViewById(R.id.img_contacts);
        img_discover= (ImageView) findViewById(R.id.img_discover);
        img_me= (ImageView) findViewById(R.id.img_me);

        tv_wechat= (TextView) findViewById(R.id.tv_wechat);
        tv_contacts= (TextView) findViewById(R.id.tv_contacts);
        tv_discover= (TextView) findViewById(R.id.tv_discover);
        tv_me= (TextView) findViewById(R.id.tv_nav_me);

        viewPager = (ViewPager) findViewById(R.id.viewpager_wechat);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ll_nav_wechat:
                changeStyle(0);
                switchFrgmt(0);
                break;
            case R.id.ll_nav_contacts:
                changeStyle(1);
                switchFrgmt(1);
                break;
            case R.id.ll_nav_discover:
                changeStyle(2);
                switchFrgmt(2);
                break;
            case R.id.ll_nav_me:
                changeStyle(3);
                switchFrgmt(3);
                break;

            case R.id.img_wechat:
                changeStyle(0);
                switchFrgmt(0);
                break;
            case R.id.img_contacts:
                changeStyle(1);
                switchFrgmt(1);
                break;
            case R.id.img_discover:
                changeStyle(2);
                switchFrgmt(2);
                break;
            case R.id.img_me:
                changeStyle(3);
                switchFrgmt(3);
                break;

            case R.id.tv_wechat:
                changeStyle(0);
                switchFrgmt(0);
                break;
            case R.id.tv_contacts:
                changeStyle(1);
                switchFrgmt(1);
                break;
            case R.id.tv_discover:
                changeStyle(2);
                switchFrgmt(2);
                break;
            case R.id.tv_nav_me:
                changeStyle(3);
                switchFrgmt(3);
                break;

        }
    }

    private void switchFrgmt(int locate) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFrgmt(ft);

        switch (locate)
        {
            case 0:
                if(fragmentWechat==null)
                {
                    fragmentWechat=new FragmentWeChat();
                    ft.add(R.id.display_page,fragmentWechat);

                }else
                {
                    ft.show(fragmentWechat);
                }
                break;
            case 1:
                if(fragmentContacts==null)
                {
                    fragmentContacts=new FragmentContacts();
                    ft.add(R.id.display_page,fragmentContacts);
                }else
                {
                    ft.show(fragmentContacts);
                }
                break;
            case 2:
               if(fragmentDiscover==null)
               {
                   fragmentDiscover=new FragmentDiscover();
                   ft.add(R.id.display_page,fragmentDiscover);
               }else
               {
                   ft.show(fragmentDiscover);
               }
                break;
            case 3:
                if(fragmentMe==null)
                {
                    fragmentMe=new FragmentMe();
                    ft.add(R.id.display_page,fragmentMe);
                }else
                {
                    ft.show(fragmentMe);
                }
                break;
        }

        ft.commit();
        viewPagerAdpter.notifyDataSetChanged();
        viewPager.setCurrentItem(locate);

    }

    private void hideAllFrgmt(FragmentTransaction ft) {

        if(fragmentWechat!=null)
        {
            ft.hide(fragmentWechat);
        }
        if(fragmentContacts!=null)
        {
            ft.hide(fragmentContacts);
        }
        if(fragmentDiscover!=null)
        {
            ft.hide(fragmentDiscover);
        }
        if(fragmentMe!=null)
        {
            ft.hide(fragmentMe);
        }

    }

    private void changeStyle(int locate) {
        colorReset();
        switch (locate)
        {
            case 0:
                img_wechat.setImageResource(R.mipmap.wechat_focused);
                tv_wechat.setTextColor(getResources().getColor(R.color.colorWeChatGreen));
                break;
            case 1:
                img_contacts.setImageResource(R.mipmap.contacts_focused);
                tv_contacts.setTextColor(getResources().getColor(R.color.colorWeChatGreen));
                break;
            case 2:
                img_discover.setImageResource(R.mipmap.discover_focused);
                tv_discover.setTextColor(getResources().getColor(R.color.colorWeChatGreen));
                break;
            case 3:
                img_me.setImageResource(R.mipmap.me_focused);
                tv_me.setTextColor(getResources().getColor(R.color.colorWeChatGreen));
                break;
        }
    }

    private void colorReset() {
        img_wechat.setImageResource(R.mipmap.wechat_un);
        img_contacts.setImageResource(R.mipmap.contacts_un);
        img_discover.setImageResource(R.mipmap.discover_un);
        img_me.setImageResource(R.mipmap.me_un);

        tv_wechat.setTextColor(getResources().getColor(R.color.colorWeChatGray));
        tv_contacts.setTextColor(getResources().getColor(R.color.colorWeChatGray));
        tv_discover.setTextColor(getResources().getColor(R.color.colorWeChatGray));
        tv_me.setTextColor(getResources().getColor(R.color.colorWeChatGray));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeStyle(position);
        switchFrgmt(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class WeChatViewPagerAdpter extends FragmentPagerAdapter {


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }

        public WeChatViewPagerAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}

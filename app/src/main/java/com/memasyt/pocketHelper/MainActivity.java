package com.memasyt.pocketHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.memasyt.pocketHelper.R;
import com.memasyt.pocketHelper.fragments.InfoFragment;
import com.memasyt.pocketHelper.fragments.MainFragment;
import com.memasyt.pocketHelper.fragments.TasksFragment;
import com.memasyt.pocketHelper.fragments.info.FormulasFragment;
import com.memasyt.pocketHelper.fragments.info.GeometryInfoFragment;
import com.memasyt.pocketHelper.fragments.info.SquareCalcFragment;
import com.memasyt.pocketHelper.fragments.info.SquareUravneniyaFragment;
import com.memasyt.pocketHelper.fragments.info.TrigonometryBaseFragment;
import com.memasyt.pocketHelper.statistic.MainStatistic;

public class MainActivity extends AppCompatActivity {

    private ImageButton main_button, tasks_button, info_button;
    private ImageView mail_button, projectInfo_button;

    private ImageView menuNowImage;

    public static int nowMenuID = 0;

    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainStatistic.activateStats(this);

        menuNowImage = findViewById(R.id.menuNow_image);
        mail_button = findViewById(R.id.mail_button);
        projectInfo_button = findViewById(R.id.info_project_button);
        if (nowMenuID == 0) {
            btn_main_click(main_button);
            mail_button.setVisibility(View.VISIBLE);
            projectInfo_button.setVisibility(View.VISIBLE);
        } else if (nowMenuID == -1) {
            btn_task_click(tasks_button);
            mail_button.setVisibility(View.INVISIBLE);
            projectInfo_button.setVisibility(View.INVISIBLE);
        }

    }


    public void btn_main_click(View v) {
        MainFragment main_frag = new MainFragment();
        nextFragment(main_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.5f);
        cs.applyTo(cl);

        nowMenuID = 0;

        mail_button.setVisibility(View.VISIBLE);
        projectInfo_button.setVisibility(View.VISIBLE);
    }

    public void btn_task_click(View v) {
        TasksFragment tasks_frag = new TasksFragment();
        nextFragment(tasks_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.02f);
        cs.applyTo(cl);

        nowMenuID = -1;
        mail_button.setVisibility(View.INVISIBLE);
        projectInfo_button.setVisibility(View.INVISIBLE);
    }

    public void btn_info_click(View v) {
        InfoFragment info_frag = new InfoFragment();
        nextFragment(info_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.98f);
        cs.applyTo(cl);

        nowMenuID = 1;
        mail_button.setVisibility(View.INVISIBLE);
        projectInfo_button.setVisibility(View.INVISIBLE);
    }

    private void nextFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void btn_square_click(View v) {
        SquareUravneniyaFragment squareInfo_frag = new SquareUravneniyaFragment();
        nextFragment(squareInfo_frag);
    }

    public void btn_formulas_click(View v) {
        FormulasFragment squareInfo_frag = new FormulasFragment();
        nextFragment(squareInfo_frag);
    }

    public void btn_trigonometryBase_click(View v) {
        TrigonometryBaseFragment trigonometryBase_frag = new TrigonometryBaseFragment();
        nextFragment(trigonometryBase_frag);
    }

    public void btn_geometry_click(View v) {
        GeometryInfoFragment geometryBase_frag = new GeometryInfoFragment();
        nextFragment(geometryBase_frag);
    }

    public void btn_square_calc(View v) {
        SquareCalcFragment squareCalc_frag = new SquareCalcFragment();
        nextFragment(squareCalc_frag);
    }
    @Override
    public void onBackPressed() {
        if(nowMenuID != 0) {
            btn_main_click(new View(this));
        }else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                this.finishAffinity();
            } else {
                Toast.makeText(getBaseContext(), "Нажмите ещё раз для выхода!", Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    public void showInfoProject(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("О приложении")
                .setMessage("Разработчик: Пшеничников Павел\nДизайнер: Пшеничников Павел\n\nПримеры, представленные в приложении, созданы автором. Копирование примеров без указания автора запрещается!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void mailClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:memasyt228@gmail.com?subject=" + Uri.encode("Карманный тренер") + "&body=" + Uri.encode(""));
        intent.setData(data);
        startActivity(intent);
    }


}
package vn.edu.ntu.truonghoangkhoa.de2;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.Calendar;

public class themFragment extends Fragment {

    EditText edtngay, edtmua, edtban;
    ImageView imglich;
    RadioButton rbthegioi, rbsjc, rbdoji;
    Button btnthem, btnxem;
    Toolbar toolbar;

    String thegioi;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them, container, false);
        addview(view);
        getdata();
        addevent();
        return view;
    }

    private void addview(View view) {
        edtngay = view.findViewById(R.id.edtngay);
        edtmua = view.findViewById(R.id.edtmuavao);
        edtban = view.findViewById(R.id.edtbanra);
        imglich = view.findViewById(R.id.imglich);
        rbthegioi = view.findViewById(R.id.rbthegioi);
        rbsjc = view.findViewById(R.id.rbsjc);
        rbdoji = view.findViewById(R.id.rbdoji);
        btnthem = view.findViewById(R.id.btnthem);
        btnxem = view.findViewById(R.id.btnxem);
        toolbar = view.findViewById(R.id.tbthem);
        toolbar.inflateMenu(R.menu.menu);

        navController = NavHostFragment.findNavController(themFragment.this);
    }

    private void getdata() {
        imglich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(year)
                                .append("-")
                                .append(++month)
                                .append("-")
                                .append(dayOfMonth);
                        edtngay.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void addevent() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbthegioi.isChecked())
                    thegioi = "Thế giới";
                if(rbsjc.isChecked())
                    thegioi = "SJC";
                if(rbdoji.isChecked())
                    thegioi = "DOJI";

                ((MainActivity)getActivity()).danhsach += edtngay.getText().toString() + "\n" + thegioi + "\n Mua vào:" + edtmua.getText().toString()
                        +"  Bán ra: " + edtban.getText().toString() + "\n";

                edtngay.setText("");
                edtmua.setText("");
                edtban.setText("");
            }
        });

        btnxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("ds",((MainActivity)getActivity()).danhsach);
                navController.navigate(R.id.action_themFragment_to_hienthiFragment,data);
            }
        });
    }
}
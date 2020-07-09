package vn.edu.ntu.truonghoangkhoa.de2;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class hienthiFragment extends Fragment {

    TextView txthienthi;
    Toolbar toolbar;

    String danhsach;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hienthi, container, false);
        addview(view);
        getdata();
        addevent();
        return view;
    }

    private void addview(View view) {
        txthienthi = view.findViewById(R.id.txthienthi);
        toolbar = view.findViewById(R.id.tbhienthi);
        toolbar.inflateMenu(R.menu.menu);

        navController = NavHostFragment.findNavController(hienthiFragment.this);
    }

    private void getdata() {
        Bundle data = getArguments();
        danhsach = data.getString("ds");
        txthienthi.setText(danhsach);
    }

    private void addevent() {
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_hienthiFragment_to_themFragment);
            }
        });
    }
}
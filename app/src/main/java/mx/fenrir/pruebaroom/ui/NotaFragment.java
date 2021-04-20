package mx.fenrir.pruebaroom.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mx.fenrir.pruebaroom.NuevaNotaDialogFragment;
import mx.fenrir.pruebaroom.NuevaNotaDialogViewModel;
import mx.fenrir.pruebaroom.R;
import mx.fenrir.pruebaroom.db.entity.NotaEntity;

public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NotaEntity> notaEntityList;
    private MyNotaRecyclerViewAdapter adapterNotas;
    private NuevaNotaDialogViewModel notaDialogViewModel;



    public NotaFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if(view.getId()==R.id.listPortrait){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
                float dpWith=displayMetrics.widthPixels/displayMetrics.density;
                int numeroColumnas=(int) (dpWith/180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas,StaggeredGridLayoutManager.VERTICAL));
            }

            notaEntityList =new ArrayList<>();

            

            adapterNotas=new MyNotaRecyclerViewAdapter(notaEntityList, getActivity());
            recyclerView.setAdapter(adapterNotas);
            
            
            lanzarViewModel();



        }
        return view;
    }

    private void lanzarViewModel() {

        notaDialogViewModel= ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);


        notaDialogViewModel.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(@Nullable List<NotaEntity> notaEntities) {
                adapterNotas.setNuevasNotas(notaEntities);
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment,menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_nota:
                mostrarDialogoNuevaNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void mostrarDialogoNuevaNota() {

        FragmentManager fm=getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogNuevaNota=new NuevaNotaDialogFragment();
        dialogNuevaNota.show(fm,"NuevaNotaDialogFragment");

    }
}

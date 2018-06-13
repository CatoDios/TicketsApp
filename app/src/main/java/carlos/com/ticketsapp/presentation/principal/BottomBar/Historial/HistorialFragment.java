package carlos.com.ticketsapp.presentation.principal.BottomBar.Historial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import carlos.com.ticketsapp.R;
import carlos.com.ticketsapp.core.BaseFragment;
import carlos.com.ticketsapp.data.models.FaltaEntity;
import carlos.com.ticketsapp.data.models.Semana_card;
import carlos.com.ticketsapp.presentation.principal.BottomBar.Semana.ItemSemana;
import carlos.com.ticketsapp.presentation.principal.BottomBar.Semana.SemanaAdapter;
import carlos.com.ticketsapp.presentation.principal.PrincipalFragment;

public class HistorialFragment extends BaseFragment implements HistorialContract.View{
    Unbinder unbinder;
    HistorialContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    HistorialAdapter mAdapter;
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getFaltas();

    }

    public static PrincipalFragment newInstance() {
        return new PrincipalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historial, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=new HistorialPresenter(this,getContext());
    }

    @Override
    public void setPresenter(HistorialContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void getFaltas(ArrayList<FaltaEntity> body) {
        mAdapter.setItems(body);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new HistorialAdapter(new ArrayList<FaltaEntity>(), getContext());
        rvList.setLayoutManager(mLayoutManager);
        rvList.setAdapter(mAdapter);
    }
}

package mx.fenrir.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;
import mx.fenrir.pruebaroom.db.entity.NotaEntity;

public class NuevaNotaDialogViewModel extends AndroidViewModel {

    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;


    public NuevaNotaDialogViewModel(Application application){
        super(application);
        notaRepository = new NotaRepository(application);
        allNotas=notaRepository.getAll();
    }


    //El framneto que necesita recibir la lista de datos

    public LiveData<List<NotaEntity>> getAllNotas() {
        return allNotas;
    }


    //El fragmento que inserte una nueva nota, debera enviarlo a este viewmodel

    public void insertaNota(NotaEntity nuevaNotaEntity){

        notaRepository.insert(nuevaNotaEntity);

    }



    public void updateNota(NotaEntity notaActualizarEntity){

        notaRepository.update(notaActualizarEntity);

    }


}

package mx.fenrir.pruebaroom;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import mx.fenrir.pruebaroom.db.NotaRoomDatabase;
import mx.fenrir.pruebaroom.db.dao.NotaDAO;
import mx.fenrir.pruebaroom.db.entity.NotaEntity;

public class NotaRepository {

    private NotaDAO notaDAO;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application){

        NotaRoomDatabase db=NotaRoomDatabase.getDatabase(application);
        notaDAO = db.notaDAO();
        allNotas=notaDAO.getAll();
        allNotasFavoritas=notaDAO.getAllFavoritas();

    }

    public LiveData<List<NotaEntity>> getAll() { return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavoritas() { return allNotasFavoritas; }

    public void insert (NotaEntity nota){
        new insertAsyncTask(notaDAO).execute(nota);
    }

    public void update(NotaEntity nota) {new updateAsyncTask(notaDAO).execute(nota); }

    private static class insertAsyncTask extends AsyncTask<NotaEntity,Void,Void>{

        private NotaDAO notaDAOAsynTask;

        insertAsyncTask(NotaDAO dao){
            notaDAOAsynTask=dao;
        }


        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {

            notaDAOAsynTask.insert(notaEntities[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<NotaEntity,Void,Void>{

        private NotaDAO notaDAOAsynTask;

        updateAsyncTask(NotaDAO dao){
            notaDAOAsynTask=dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDAOAsynTask.update(notaEntities[0]);
            return null;
        }
    }





}

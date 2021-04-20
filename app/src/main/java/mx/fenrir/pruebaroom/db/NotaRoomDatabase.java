package mx.fenrir.pruebaroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mx.fenrir.pruebaroom.db.dao.NotaDAO;
import mx.fenrir.pruebaroom.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class},version = 1)
//del curso
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract NotaDAO notaDAO();

    private static  volatile  NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context){

        if(INSTANCE==null){

            synchronized ((NotaRoomDatabase.class)){

                if(INSTANCE==null){

                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),NotaRoomDatabase.class,
                            "notas_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}

package tn.app.boostiny.mvvmexample.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tn.app.boostiny.mvvmexample.model.Project;

public class ProjectRepository {

    private GithubService gitHubService;
    private static ProjectRepository projectRepository;
    private final static String TAG = "PROJECT_REPOSITORY";

    private ProjectRepository() {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GithubService.class);
    }

    public synchronized static ProjectRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();

        }
        return projectRepository;
    }

    public LiveData<List<Project>> getProjectList(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
                Log.e(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }


    public LiveData<Project> getProjectDetails(String user, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();

        Log.e(TAG, "getProjectDetails: " + gitHubService.getProjectDetails(user, projectName).request().url().toString()
        );


        gitHubService.getProjectDetails(user, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {

                 simulateDelay();
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {

                data.setValue(null);
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

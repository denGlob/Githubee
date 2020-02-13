package com.denshiksmle.githubee.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.denshiksmle.githubee.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigation();
    }

    private void initNavigation() {
        navController = Navigation.findNavController(this, R.id.fragment);
        navController.setGraph(R.navigation.nav_graph);
        NavigationUI.setupWithNavController(toolbar, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int destinationId = destination.getId();
//            destViewStrategy(destinationId);
        });
    }
}

package com.example.examenmovprimertrim_davidroldan.framework.main;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\rH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J&\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010$\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u001a\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\'2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006/"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/examenmovprimertrim_davidroldan/databinding/FragmentEquiposBinding;", "actionMode", "Landroidx/appcompat/view/ActionMode;", "anteriorState", "Lcom/example/rest_davidroldan/framework/main/EquiposState;", "binding", "getBinding", "()Lcom/example/examenmovprimertrim_davidroldan/databinding/FragmentEquiposBinding;", "callback", "Landroidx/appcompat/view/ActionMode$Callback;", "getCallback", "()Landroidx/appcompat/view/ActionMode$Callback;", "callback$delegate", "Lkotlin/Lazy;", "equiposAdapter", "Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter;", "viewModel", "Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposViewModel;", "getViewModel", "()Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposViewModel;", "viewModel$delegate", "click", "", "id", "Ljava/util/UUID;", "name", "", "configContextBar", "observarViewModel", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "app_debug"})
public final class EquiposFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.examenmovprimertrim_davidroldan.databinding.FragmentEquiposBinding _binding;
    private com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter equiposAdapter;
    @org.jetbrains.annotations.Nullable
    private com.example.rest_davidroldan.framework.main.EquiposState anteriorState;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private androidx.appcompat.view.ActionMode actionMode;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy callback$delegate = null;
    
    public EquiposFragment() {
        super();
    }
    
    private final com.example.examenmovprimertrim_davidroldan.databinding.FragmentEquiposBinding getBinding() {
        return null;
    }
    
    private final com.example.examenmovprimertrim_davidroldan.framework.main.EquiposViewModel getViewModel() {
        return null;
    }
    
    private final androidx.appcompat.view.ActionMode.Callback getCallback() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observarViewModel() {
    }
    
    private final void click(java.util.UUID id, java.lang.String name) {
    }
    
    @java.lang.Override
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull
    android.view.Menu menu, @org.jetbrains.annotations.NotNull
    android.view.MenuInflater inflater) {
    }
    
    private final androidx.appcompat.view.ActionMode.Callback configContextBar() {
        return null;
    }
}
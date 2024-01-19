package com.example.examenmovprimertrim_davidroldan.framework.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0003#$%B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001c\u0010\u001a\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\u0006\u0010\u001e\u001a\u00020\u0016J\u0014\u0010\u001f\u001a\u00020\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020!J\u0006\u0010\"\u001a\u00020\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006&"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/examen/domain/Equipo;", "Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$ItemViewholder;", "context", "Landroid/content/Context;", "actions", "Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$EquiposActions;", "(Landroid/content/Context;Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$EquiposActions;)V", "getActions", "()Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$EquiposActions;", "getContext", "()Landroid/content/Context;", "selectedEquipos", "", "selectedMode", "", "swipeGesture", "Lcom/example/rest_davidroldan/framework/main/SwipeGesture;", "getSwipeGesture", "()Lcom/example/rest_davidroldan/framework/main/SwipeGesture;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "resetSelectMode", "setSelectedItems", "customersSeleccionados", "", "startSelectMode", "DiffCallback", "EquiposActions", "ItemViewholder", "app_debug"})
public final class EquiposAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.examen.domain.Equipo, com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.ItemViewholder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.EquiposActions actions = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.examen.domain.Equipo> selectedEquipos;
    private boolean selectedMode = false;
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.framework.main.SwipeGesture swipeGesture = null;
    
    public EquiposAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.EquiposActions actions) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.EquiposActions getActions() {
        return null;
    }
    
    public final void startSelectMode() {
    }
    
    public final void resetSelectMode() {
    }
    
    public final void setSelectedItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.examen.domain.Equipo> customersSeleccionados) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.ItemViewholder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.framework.main.EquiposAdapter.ItemViewholder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.rest_davidroldan.framework.main.SwipeGesture getSwipeGesture() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/examen/domain/Equipo;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.examen.domain.Equipo> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo oldItem, @org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo oldItem, @org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\r"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$EquiposActions;", "", "itemHasClicked", "", "equipo", "Lcom/example/examen/domain/Equipo;", "onClickItem", "equipoId", "Ljava/util/UUID;", "name", "", "onDelete", "onStartSelectMode", "app_debug"})
    public static abstract interface EquiposActions {
        
        public abstract void onDelete(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo equipo);
        
        public abstract void onStartSelectMode(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo equipo);
        
        public abstract void itemHasClicked(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo equipo);
        
        public abstract void onClickItem(@org.jetbrains.annotations.NotNull
        java.util.UUID equipoId, @org.jetbrains.annotations.NotNull
        java.lang.String name);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter$ItemViewholder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposAdapter;Landroid/view/View;)V", "binding", "Lcom/example/examenmovprimertrim_davidroldan/databinding/ViewEquiposBinding;", "bind", "", "item", "Lcom/example/examen/domain/Equipo;", "app_debug"})
    public final class ItemViewholder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.examenmovprimertrim_davidroldan.databinding.ViewEquiposBinding binding = null;
        
        public ItemViewholder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo item) {
        }
    }
}
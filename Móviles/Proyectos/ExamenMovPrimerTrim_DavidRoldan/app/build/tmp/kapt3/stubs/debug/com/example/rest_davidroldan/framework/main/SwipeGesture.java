package com.example.rest_davidroldan.framework.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J@\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J \u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0019"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/SwipeGesture;", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "deleteColor", "", "getDeleteColor", "()I", "onChildDraw", "", "c", "Landroid/graphics/Canvas;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "dX", "", "dY", "actionState", "isCurrentlyActive", "", "onMove", "target", "app_debug"})
public abstract class SwipeGesture extends androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback {
    private final int deleteColor = 0;
    
    public SwipeGesture(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(0, 0);
    }
    
    public final int getDeleteColor() {
        return 0;
    }
    
    @java.lang.Override
    public boolean onMove(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, @org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder target) {
        return false;
    }
    
    @java.lang.Override
    public void onChildDraw(@org.jetbrains.annotations.NotNull
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    }
}
// Generated by Dagger (https://dagger.dev).
package com.example.rest_davidroldan.framework.orders;

import com.example.rest_davidroldan.domain.usecases.DeleteOrderUseCase;
import com.example.rest_davidroldan.domain.usecases.GetCustomerIdUseCase;
import com.example.rest_davidroldan.domain.usecases.GetOrdersUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class OrdersViewModel_Factory implements Factory<OrdersViewModel> {
  private final Provider<GetCustomerIdUseCase> getCustomerIdUseCaseProvider;

  private final Provider<GetOrdersUseCase> getOrdersUseCaseProvider;

  private final Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider;

  public OrdersViewModel_Factory(Provider<GetCustomerIdUseCase> getCustomerIdUseCaseProvider,
      Provider<GetOrdersUseCase> getOrdersUseCaseProvider,
      Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider) {
    this.getCustomerIdUseCaseProvider = getCustomerIdUseCaseProvider;
    this.getOrdersUseCaseProvider = getOrdersUseCaseProvider;
    this.deleteOrderUseCaseProvider = deleteOrderUseCaseProvider;
  }

  @Override
  public OrdersViewModel get() {
    return newInstance(getCustomerIdUseCaseProvider.get(), getOrdersUseCaseProvider.get(), deleteOrderUseCaseProvider.get());
  }

  public static OrdersViewModel_Factory create(
      Provider<GetCustomerIdUseCase> getCustomerIdUseCaseProvider,
      Provider<GetOrdersUseCase> getOrdersUseCaseProvider,
      Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider) {
    return new OrdersViewModel_Factory(getCustomerIdUseCaseProvider, getOrdersUseCaseProvider, deleteOrderUseCaseProvider);
  }

  public static OrdersViewModel newInstance(GetCustomerIdUseCase getCustomerIdUseCase,
      GetOrdersUseCase getOrdersUseCase, DeleteOrderUseCase deleteOrderUseCase) {
    return new OrdersViewModel(getCustomerIdUseCase, getOrdersUseCase, deleteOrderUseCase);
  }
}

// Generated by Dagger (https://dagger.dev).
package com.example.rest_davidroldan.domain.usecases;

import com.example.rest_davidroldan.data.repositories.OrderRepository;
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
public final class AddOrderUseCase_Factory implements Factory<AddOrderUseCase> {
  private final Provider<OrderRepository> repositoryProvider;

  public AddOrderUseCase_Factory(Provider<OrderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddOrderUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddOrderUseCase_Factory create(Provider<OrderRepository> repositoryProvider) {
    return new AddOrderUseCase_Factory(repositoryProvider);
  }

  public static AddOrderUseCase newInstance(OrderRepository repository) {
    return new AddOrderUseCase(repository);
  }
}

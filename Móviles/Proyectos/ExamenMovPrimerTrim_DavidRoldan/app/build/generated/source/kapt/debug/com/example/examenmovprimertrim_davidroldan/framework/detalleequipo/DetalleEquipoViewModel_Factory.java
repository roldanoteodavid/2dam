// Generated by Dagger (https://dagger.dev).
package com.example.examenmovprimertrim_davidroldan.framework.detalleequipo;

import com.example.examenmovprimertrim_davidroldan.domain.usecases.GetJugadoresEquipoUseCase;
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
public final class DetalleEquipoViewModel_Factory implements Factory<DetalleEquipoViewModel> {
  private final Provider<GetJugadoresEquipoUseCase> getJugadoresEquipoUseCaseProvider;

  public DetalleEquipoViewModel_Factory(
      Provider<GetJugadoresEquipoUseCase> getJugadoresEquipoUseCaseProvider) {
    this.getJugadoresEquipoUseCaseProvider = getJugadoresEquipoUseCaseProvider;
  }

  @Override
  public DetalleEquipoViewModel get() {
    return newInstance(getJugadoresEquipoUseCaseProvider.get());
  }

  public static DetalleEquipoViewModel_Factory create(
      Provider<GetJugadoresEquipoUseCase> getJugadoresEquipoUseCaseProvider) {
    return new DetalleEquipoViewModel_Factory(getJugadoresEquipoUseCaseProvider);
  }

  public static DetalleEquipoViewModel newInstance(
      GetJugadoresEquipoUseCase getJugadoresEquipoUseCase) {
    return new DetalleEquipoViewModel(getJugadoresEquipoUseCase);
  }
}

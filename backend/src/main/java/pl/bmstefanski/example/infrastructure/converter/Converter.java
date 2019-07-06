package pl.bmstefanski.example.infrastructure.converter;

@FunctionalInterface
interface Converter<R, T> {

  R convert(T value);

}

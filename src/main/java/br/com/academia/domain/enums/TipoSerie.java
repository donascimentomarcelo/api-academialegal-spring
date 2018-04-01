package br.com.academia.domain.enums;

public enum TipoSerie {
	
	HIPERTROFIA(1, "Hipertrofia"),
	DEFINICAO(2, "Definição"),
	RESISTENCIA(3, "Resistência"),
	OUTROS(4, "Outros");
	
	private int codigo;
	private String descricao;
	
	private TipoSerie(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	//@JsonValue
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	//@JsonCreator
	public static TipoSerie toEnum(Integer codigo)
	{
		if(codigo == null)
		{
			return null;
		}
		
		for (TipoSerie serie: TipoSerie.values())
		{
			if(codigo.equals(serie.getCodigo()))
			{
				return serie;
			}
		}
		throw new IllegalArgumentException("Código inválido" + codigo);
	}
	
	
}

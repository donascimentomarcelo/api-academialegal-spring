package br.com.academia.domain.enums;

public enum TipoSerie {
	
	PENDENTE(1, "Pendente"),
	CONCLUIDO(2, "Concluido"),
	REJEITADO(3, "Rejeitado");
	
	private int codigo;
	private String descricao;
	
	private TipoSerie(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

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

package br.com.academia.domain.enums;

public enum StatusSerie {
	
	PENDENTE(1, "Pendente"),
	CONCLUIDO(2, "Concluído"),
	REJEITADO(3, "Rejeitado");
	
	private int codigo;
	private String descricao;
	
	private StatusSerie(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusSerie toEnum(Integer codigo)
	{
		if(codigo == null)
		{
			return null;
		}
		
		for (StatusSerie serie: StatusSerie.values())
		{
			if(codigo.equals(serie.getCodigo()))
			{
				return serie;
			}
		}
		throw new IllegalArgumentException("Código inválido" + codigo);
	}
	
}

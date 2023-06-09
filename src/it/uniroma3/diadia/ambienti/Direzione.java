package it.uniroma3.diadia.ambienti;

public enum Direzione {

	nord() {
		@Override 
		public Direzione opposta() {
			return sud;
		}
	},
	
	sud() {
		@Override 
		public Direzione opposta() {
			return nord;
		}
	},
	
	est() {
		@Override 
		public Direzione opposta() {
			return ovest;
		}
	},
	
	ovest() {
		@Override 
		public Direzione opposta() {
			return est;
		}
	};
	public abstract Direzione opposta();
}

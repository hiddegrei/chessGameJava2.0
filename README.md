# Chess game in Java met een gebruikers user interface.
Game heeft op 'stale mate' na alle eigenschappen van een schaak spel. 
Dit betekend:
- De mogelijke posities van de king worden bepaald.
- Bij een check wordt bekeken of er een stuk is dat de check kan blokkeren of uitschakelen.
- Checkmate detectie.
- Rokeren, hierbij wordt rekening gehouden met de mogelijkheid dat een stuk van de tegenstander het rokeren kan tegengaan.
- Legale zetten worden bepaalde voor de stukken.


Om de game te runnen moet u het ChessFrame.java bestand in /src/chessgui runnen.

github link: https://github.com/hiddegrei/chessGameHZ

## Creational Patterns

**Factory method**

In de chess game heb ik gebruikt van de factory method. De Queen, King, Rook, Pawn and Knight classes en
extenden allemaal de Piece class en implementeren hun eigen methoden voor bijvoorbeeld het bepalen van een legale zet.
```diff
- onderstaand is voor resit

```
Ik maak nu gebruik van verschillende factory classes voor het maken van de verschillende stukken. Deze factory classes zijn te vinden in de pieceFactory folder en maken gebruik van de PieceFactory interface. 
Er is dus nu een KingFactory, QueenFactory, RookFactory, KnightFactory, BishopFactory en PawnFactory. Deze worden nu gebruikt wanneer in de ChessBoard.java class de methode initializeBoard() wordt aangeroepen.



**Prototype**

De posities van het chess board kunnen gekopieerd worden waarbij er voor elke position een nieuwe instantie van een Position class wordt aangemaakt
met een nieuwe instantie van de bijbehorende Piece class. De Position en Piece klassen hebben hier hun eigen methode voor het clonen van zichzelf.

## Structual Patterns

**Decorator**

De Queen, King, Rook, Pawn en Knight classes implementeren hun eigen versie van de checkLegalMove() functie, elk soort stuk heeft namelijk zijn eigen eisen voor het bepalen van een legale zet.
Deze heeft wordt overgeërfd van de Piece class.

```diff
- onderstaand is voor resit

```
MethodDecorator class toegevoegd, te vinden in de methodDecorator folder.

**Composite**

De Game class heeft positions, de positions bestaan uit individuele positions en de positions bevatten zelf een Piece. Terwijl Piece een superclass is van de individuele stukken 
Queen, King, Rook, Pawn en Knight.


## Behavioral Patterns

**Chain of Responsibility**

het chessboard heeft een arraylist van positions waarbij elke position een instantie is van de Position class die verantwoordelijk is voor het bijhouden van de positie op het chessboard
en heeft zelf een instantie van Piece die dus hoort bij die locatie op het chessboard. De instantie van Piece geeft aan welk soort piece er op die positie staat en deze instantie van Piece is verantwoordelijk voor 
het bepalen van legale moves.

**Template Method**

De Piece class heeft de abstracte methoden checkLegalMove die door elke instantie van De Queen, King, Rook, Pawn and Knight classes word ovverride. Daarnaast heet de Piece class algemen methoden, zoals checkStraight en checkDia die checkt of een piece horizontaal, verticaal of diagonaal kan bewegen. De Queen, King, Rook classes gebruiken deze methoden in hun eigen checkLegalMove methode om te bepalen of de gegeven zet legaal is. 



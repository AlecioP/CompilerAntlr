# CompilerAntlr
Final project for Compilers' course

# Design specification in IT :

Progetto 2020

1) Realizzare un sistema di ANALISI SEMANTICA per il linguaggio SimplePlus in allegato.
In  particolare, il sistema deve controllare

* di variabili/funzioni non dichiarate
* di variabili dichiarate piu' volte nello stesso ambiente (in questa analisi e'
  corretto il codice int x = 4 ; delete x ; int x = 5 ;)
* parametri attuali non conformi ai parametri formali (inclusa la verifica sui 
  parametri passati per var)
* la correttezza dei tipi 

Inoltre deve controllare gli accessi a identificatori "cancellati" con particolare
attenzione all'aliasing implementando il sistema visto a lezione

2) Definire un linguaggio bytecode per eseguire programmi in SimplePlus, scrivere 
la compilazione e implementare l'interprete. In particolare:

* Il bytecode deve avere istruzioni per una macchina a pila che memorizza in un 
   apposito registro il valore dell'ultima istruzione calcolata [vedi slide delle lezioni]
* Implementare l'interprete per il bytecode.
* Compilare ed eseguire i programmi del linguaggio ad alto livello.


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;    
entity ATM is
port(	  
confirm:in std_logic;
card_pin : in std_logic_vector(4 downto 0);
comanda:in std_logic_vector(1 downto 0);
clock: in std_logic;
suma_zeci: in std_logic;
suma_sute: in std_logic;
suma_mii: in std_logic;	
anod: out std_logic_vector(3 downto 0);
catod: out std_logic_vector(6 downto 0);
ok_chitanta,ok_card: out std_logic;
chitanta: in std_logic);
end ATM;
architecture Structural of ATM is	
--divizor
component clock_divider is
	port(clk: in std_logic;
	r: in std_logic;
	clk_out: out std_logic);	
end component;
--bcd 7 seg
component SSD is
Port ( clock : in STD_LOGIC;
sute : in STD_LOGIC_VECTOR(3 downto 0);
mii : in STD_LOGIC_VECTOR(3 downto 0);
zeci : in STD_LOGIC_VECTOR(3 downto 0);
an : out STD_LOGIC_VECTOR (3 downto 0);
cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;
-- counter
component num_dir is
port(
clk, en: in std_logic;
q: out std_logic_vector (3 downto 0));
end component;
--registru bancnote
component reg_univ is
port(
d_in : in integer; --range 0 to 30;
clk:in std_logic;
q : out integer );--range 0 to 30);
end component;
-- registru card+pin
component reg_univ1 is
port(
d_in :in std_logic_vector(4 downto 0);
clk:  in std_logic;
q : out std_logic_vector(4 downto 0));
end component;
-- dmux12
component mux_chitanta is
port (A: in std_logic;
B: in std_logic;
S: in std_logic;
Q: out std_logic);
end component;
----verificare
component cardpin is
	port( card_pin: in std_logic_vector (4 downto 0);
	ok_card: out std_logic);
end component;
--registru suma
component reg_suma is
	port( 
	d_in : in integer; --range;-- 0 to 30;
	clk: in std_logic;
	q : out integer);-- range; --0 to 30);
end component;	 
--codificator pt suma =>hexa
component sumacodif is
	port(suma: in integer;
	zeci,sute,mii: out std_logic_vector(3 downto 0));
end component; 
--mux comanda
component muxcomanda is
	port(ok:in std_logic;
	comandain:in std_logic_vector (1 downto 0);
	comandaout: out std_logic_vector (1 downto 0));
end component;
--sumator
component sumator is 
	port( confirmarebani,confirmareretragere,stop:in std_logic;
	retragere,factura: in std_logic;
	comanda: in std_logic_vector(1 downto 0);
	miiout,zeciout,suteout: std_logic_vector(3 downto 0);
	suma_introdusa:out integer;
	suma:out integer);
end component; 
--sumator bancnote
component sumatorbancnote is 
	port(confirmarebani,confirmareretragere:in std_logic;
	retragere:in std_logic;
	comanda: in std_logic_vector(1 downto 0);
	miiout,zeciout,suteout: std_logic_vector(3 downto 0);
	euro10,euro100,euro1000:out integer);
	--euro10out,euro100out,euro1000out:out integer);
end component;	
--comparator
component comparator is
port 
(comanda: in std_logic_vector(1 downto 0);
sumacard,sumaintrodusa,sumabancomat,factura: in integer;
outputfactura,outputretragere:out std_logic);
end component;	  

signal suteout,miiout,zeciout: std_logic_vector (3 downto 0);
signal suma: integer :=0;
signal euro10,euro100,euro1000:integer:=0;
signal euro10out,euro100out,euro1000out: integer:=0;
signal confirmareretragere,confirmarebani,stop: std_logic:='0';
signal chitantaOut: std_logic:='0';
signal sumaOut: integer:=0;
signal ok:std_logic:='0';	  
signal mii,sute,zeci: std_logic_vector(3 downto 0):="0000";	  
signal comandaout:std_logic_vector(1 downto 0);	  
signal sumaintrodusa: integer:=0; 
signal sumabancomat:integer:=0;	 
signal outputretragere,outputfactura:std_logic:='0';
signal factura:integer:=100;  

begin
----------------------------------NUMARATOARE--------------------------------------------------------------------------------
numarator10: num_dir port map(suma_zeci,confirm,zeciout);
numarator1000: num_dir port map(suma_mii,confirm,miiout);
numarator100: num_dir port map(suma_sute,confirm,suteout);	 
----------------------------------REGISTRE---------------------------------------------------------------------------------
registru10: reg_univ port map(euro10,confirmarebani,euro10out);--registru care tine minte numarul de bancnote de 10euro
registru100: reg_univ port map(euro100,confirmarebani,euro100out);--registru care tine minte numarul de bancnote de 100euro
registru1000: reg_univ port map(euro1000,confirmarebani,euro1000out);--registru care tine minte numarul de bancnote de 1000euro
registruSuma : reg_suma port map(suma,confirmarebani,sumaOut);--registru care tine minte suma din card;
-------------------------------COMPARATOARE--------------------------------------------------------------------------------
comparare : comparator port map (comandaout,suma,sumaintrodusa,sumabancomat,factura,outputfactura,outputretragere);	 
--comparator pentru operatiunile de retragere si plata factura
verificare: cardpin port map(card_pin,ok);-- comparator pentru combinatia de pin si card  
---------------------------------MUX-URI-----------------------------------------------------------------------------------
regim: muxcomanda port map(ok,comanda,comandaout);-- mux pentru alegerea coperatiiunii 
muxchitanta: mux_chitanta port map('0','1',chitanta,ok_chitanta); 
--mux pentru alege daca utilizatorul doreste sau nu chitanta
--------------------------------UAL-------------------------------------------------------------------
insumare:sumator port map( confirmarebani,confirmareretragere,stop,outputretragere,outputfactura,comandaout,miiout,zeciout,suteout,sumaintrodusa,suma);
--ual pentru retragere/depunere si plata factura
insumarebancnote: sumatorbancnote port map( confirmarebani,confirmareretragere,outputretragere,comandaout,miiout,zeciout,suteout,euro10,euro100,euro1000);	  
--ual pentru insumarea/scaderea numarului de bancnote din bancomat din fiecare tip
sumabancomat<=euro10*10+euro10*100+euro1000*1000;  
---------------------------------AFISAJ----------------------------------------------------------------------------
codificator: sumacodif port map(suma,zeci,sute,mii);
--codificator care va imparti suma in zeci, sute, mii pentru a le afisa pe BCD 7 seg
afisaj : SSD port map(clock,sute,mii,zeci,anod,catod);	 
------------------------------------Divizor-----------------------------------------------------------------------

end Structural;
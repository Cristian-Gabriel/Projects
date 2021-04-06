library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;

entity SSD is
Port ( clock : in STD_LOGIC;
sute : in STD_LOGIC_VECTOR(3 downto 0);
mii : in STD_LOGIC_VECTOR(3 downto 0);
zeci : in STD_LOGIC_VECTOR(3 downto 0);
an : out STD_LOGIC_VECTOR (3 downto 0);
cat : out STD_LOGIC_VECTOR (6 downto 0));
end SSD;

architecture Behavioral of SSD is
signal LED_BCD: STD_LOGIC_VECTOR (3 downto 0):="0000";
signal count: STD_LOGIC_VECTOR (15 downto 0):="0000000000000000";
signal c: std_logic_vector(1 downto 0):="00";
begin
-- catod
process(LED_BCD)
begin
case LED_BCD is
when "0000" => cat <= "1000000"; --0
when "0001" => cat <= "1111001";--1
when "0010" => cat <= "0100100"; --2
when "0011" => cat <= "0110000"; --3
when "0100" => cat <= "0011001"; --4
when "0101" => cat <= "0010010"; --5
when "0110" => cat <= "0000010"; --6
when "0111" => cat <= "0111000";--7
when "1000" => cat <= "0000000"; --8
when "1001" => cat <= "0010000"; --9
when "1010" => cat <= "0001000"; --a
when "1011" => cat <= "0000011"; --b
when "1100" => cat <= "1000110"; --c
when "1101" => cat <= "0100001"; --d
when "1110" => cat <= "0000110"; --e
when "1111" => cat <= "0001110"; --f
when others => cat <= "1111111"; --NULL
end case;
end process;

process(clock)
begin
if(rising_edge(clock)) then
count <= count + 1;
end if;
end process;

c <= count(15 downto 14);

process(c)
begin
case c is
when "00" =>
an <= "0111";
LED_BCD <= mii;
when "01" =>
an <= "1011";
LED_BCD <= sute;
when "10" =>
an <= "1101";
LED_BCD <= zeci;
when "11" =>
an <= "1110";
LED_BCD <= "0000";
when others => NULL;
end case;
end process;
end Behavioral;
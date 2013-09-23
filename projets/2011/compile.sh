#! /bin/bash

mkdir -p Boggle/build
javac -cp "Boggle/src:Boggle/lib/RadixTree-0.3.jar:Boggle/lib/patricia-trie-0.2.jar" Boggle/src/boggle/*.java -d Boggle/build



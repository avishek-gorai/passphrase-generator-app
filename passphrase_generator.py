''' Copyright (C) 2025 Avishek Gorai

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
'''

import tkinter, math

class App(tkinter.Tk):
    '''I represent the Passphrase generator application.  Methods that seem to return None return self.'''

    __minimumPassphraseLength = 6
    __appHeight = 170
    __appWidth = 800

    def __init__(self):
        super().__init__()
        self.title('Passphrase generator')
        self.geometry(f'{App.getAppWidth()}x{App.getAppHeight()}')
        self.wordList = {}
        self.passphraseFileNameStringVar = tkinter.StringVar()
        self.passphraseLabelStringVar = tkinter.StringVar()
        self.passphraseFileLabel = tkinter.Label(self, text = 'Passphrase file')
        self.passphraseFileNameLabel = tkinter.Label(self, textvariable = self.passphraseFileNameStringVar)
        self.choosePassphraseFileButton = tkinter.Button(
                                                self,
                                                text = 'Choose file',
                                                command = self.changePassphraseFileMethod
                                            )
        self.numberOfWordsLabel = tkinter.Label(self, text = 'Number of words')
        self.numberOfWordsSelector = tkinter.Spinbox(
                                            self,
                                            from_ = App.getMinimumPassphraseLength(),
                                            to = math.inf,
                                            wrap = False
                                    )
        self.passphraseLabel = tkinter.Label(self, textvariable = self.passphraseLabelStringVar)
        self.generatePassphraseButton = tkinter.Button(
                                            self,
                                            text = 'Generate passphrase',
                                            command = self.generatePassphraseMethod
                                        )
        self.copyButton = tkinter.Button(
                                    self,
                                    text = 'Copy',
                                    command = self.copyPassphraseMethod
                                  )
        self.loadPassphraseFile('eff_large_wordlist.txt')

        # Placing widgets

        # First row
        self.passphraseFileLabel.place(relx = 0, rely = 0, relwidth = 1/3)
        self.passphraseFileNameLabel.place(relx = 1/3, rely = 0, relwidth = 1/3)
        self.choosePassphraseFileButton.place(relx = 2/3, rely = 0, relwidth = 1/3)

        # Second row
        self.numberOfWordsLabel.place(relx = 0, rely = 1/5, relwidth = 1/2)
        self.numberOfWordsSelector.place(relx = 1/2, rely = 1/5, relwidth = 1/2)

        # Third row
        self.passphraseLabel.place(relx = 0, rely = 2/5, relwidth = 1)

        # Fourth row
        self.generatePassphraseButton.place(relx = 0, rely = 3/5, relwidth = 1)

        # Fifth row
        self.copyButton.place(relx = 0, rely = 4/5, relwidth = 1)

    def run(self):
        self.mainloop()
        return self

    @property
    def generatePassphraseMethod(self):
        return self.__generatePassphraseMethod      

    @property
    def passphraseLabelStringVar(self):
        return self.__passphraseLabelStringVar

    @passphraseLabelStringVar.setter
    def passphraseLabelStringVar(self, string_var):
        self.__passphraseLabelStringVar = string_var

    @property
    def changePassphraseFileMethod(self):
        return self.__changePassphraseFileMethod
    
    def __changePassphraseFileMethod(self):
        print('Passphrase file changed.')

    def __generatePassphraseMethod(self):
        print('Passphrase generated.')

    @property
    def copyPassphraseMethod(self):
        return self.__copyPassphraseMethod
    
    def __copyPassphraseMethod(self):
        print('Passphrase copied.')

    @property
    def numberOfDice(self):
        return self.__numberOfDice

    @numberOfDice.setter
    def numberOfDice(self, n):
        self.__numberOfDice = n

    @property
    def numberOfWords(self):
        return self.__numberOfWords

    @numberOfWords.setter
    def numberOfWords(self, n):
        self.__numberOfWords = n

    def loadPassphraseFile(self, file_path):        
        with open(file_path, 'r') as passphrase_file:
            lines = passphrase_file.readlines()
            self.numberOfDice = len(lines[0].split()[0])
            for line in lines:
                line = line.split()
                self.wordList.setdefault(int(line[0]), line[1])
            self.passphraseFileNameStringVar.set(passphrase_file.name)

        return self

    @property
    def passphraseFileNameStringVar(self):
        return self.__passphraseFileNameStringVar

    @passphraseFileNameStringVar.setter
    def passphraseFileNameStringVar(self, string_variable):
        self.__passphraseFileNameStringVar = string_variable

    @property
    def wordList(self):
        return self.__wordList

    @wordList.setter
    def wordList(self, dictionary):
        self.__wordList = dictionary
        
    @property
    def generatePassphraseButton(self):
        return self.__generatePassphraseButton

    @generatePassphraseButton.setter
    def generatePassphraseButton(self, button):
        self.__generatePassphraseButton = button

    @property
    def passphraseFileLabel(self):
        return self.__passphraseFileLabel

    @passphraseFileLabel.setter
    def passphraseFileLabel(self, label):
        self.__passphraseFileLabel = label

    @property
    def passphraseFileNameLabel(self):
        return self.__passphraseFileNameLabel

    @passphraseFileNameLabel.setter
    def passphraseFileNameLabel(self, label):
        self.__passphraseFileNameLabel = label

    @property
    def choosePassphraseFileButton(self):
       return self.__choosePassphraseFileButton

    @choosePassphraseFileButton.setter
    def choosePassphraseFileButton(self, button):
        self.__choosePassphraseFileButton = button

    @property
    def numberOfWordsLabel(self):
        return self.__numberOfWordsLabel

    @numberOfWordsLabel.setter
    def numberOfWordsLabel(self, label):
        self.__numberOfWordsLabel = label

    @property
    def numberOfWordsSelector(self):
        return self.__numberOfWordsSelector

    @numberOfWordsSelector.setter
    def numberOfWordsSelector(self, spinner):
        self.__numberOfWordsSelector = spinner

    @property
    def passphraseLabel(self):
        return self.__passphraseLabel

    @passphraseLabel.setter
    def passphraseLabel(self, label):
        self.__passphraseLabel = label

    @property
    def copyButton(self):
        return self.__copyButton

    @copyButton.setter
    def copyButton(self, button):
        self.__copyButton = button

    @classmethod
    def getMinimumPassphraseLength(cls):
        return cls.__minimumPassphraseLength

    @classmethod
    def getAppHeight(cls):
        return cls.__appHeight

    @classmethod
    def getAppWidth(cls):
        return cls.__appWidth


App().run()

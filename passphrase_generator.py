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
    '''I represent the Passphrase generator application.'''
    
    minimumPassphraseLength = 6
    
    def __init__(self):
        super().__init__()
        self.title('Passphrase generator')
        self.geometry('700x100')
        self.passphraseFileLabel = tkinter.Label(self, text = 'Passphrase file')
        self.passphraseFileNameLabel = tkinter.Label(self, text = 'Passphrase file name')
        self.choosePassphraseFileButton = tkinter.Button(
                                                self,
                                                text = 'Choose file',
                                                command = self.changePassphraseFile
                                            )
        self.numberOfWordsLabel = tkinter.Label(self, text = 'Number of words')
        self.numberOfWordsSelector = tkinter.Spinbox(
                                            self,
                                            from_ = self.minimumPassphraseLength,
                                            to = math.inf,
                                            wrap = False
                                    )
        self.passphraseLabel = tkinter.Label(self, text = 'Passphrase')
        self.generatePassphraseButton = tkinter.Button(
                                            self,
                                            text = 'Generate passphrase',
                                            command = self.generatePassphrase
                                        )
        self.copyButton = tkinter.Button(
                                    self,
                                    text = 'Copy',
                                    command = self.copyPassphrase
                          )

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
        self.mainloop()

    def changePassphraseFile(self):
        '''Passphrase file change method.'''
        print('Passphrase file changed.')

    def generatePassphrase(self):
        '''Passphrase generate method.'''
        print('Passphrase generated.')

    def copyPassphrase(self):
        '''Copy passphrase method.'''
        print('Passphrase copied.')

    def loadPassphraseFile(self):
        '''Loads the passphrase file.'''
        print('Passphrase file loaded.')

App()

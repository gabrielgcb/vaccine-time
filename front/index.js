function myAccFunc() {
    var x = document.getElementById("demoAcc");
    if (x.className.indexOf("w3-show") == -1) {
      x.className += " w3-show";  // Exibe o conteúdo do accordion
      x.previousElementSibling.className += " w3-green";  // Destaca o botão
    } else { 
      x.className = x.className.replace(" w3-show", "");  // Esconde o conteúdo do accordion
      x.previousElementSibling.className = 
      x.previousElementSibling.className.replace(" w3-green", "");  // Remove o destaque
    }
  }
  
  function myAccFunc2() {
    var x = document.getElementById("demoAcc2");
    if (x.className.indexOf("w3-show") == -1) {
      x.className += " w3-show";  // Exibe o conteúdo do accordion
      x.previousElementSibling.className += " w3-green";  // Destaca o botão
    } else { 
      x.className = x.className.replace(" w3-show", "");  // Esconde o conteúdo do accordion
      x.previousElementSibling.className = 
      x.previousElementSibling.className.replace(" w3-green", "");  // Remove o destaque
    }
  }
  
  function myAccFunc3() {
    var x = document.getElementById("demoAcc3");
    if (x.className.indexOf("w3-show") == -1) {
      x.className += " w3-show";  // Exibe o conteúdo do accordion
      x.previousElementSibling.className += " w3-green";  // Destaca o botão
    } else { 
      x.className = x.className.replace(" w3-show", "");  // Esconde o conteúdo do accordion
      x.previousElementSibling.className = 
      x.previousElementSibling.className.replace(" w3-green", "");  // Remove o destaque
    }
  }
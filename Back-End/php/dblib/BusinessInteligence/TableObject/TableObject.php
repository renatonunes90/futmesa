<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/TableObject.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de um objeto de tabela.
 */
class TableObject
{

   /**
    *
    * @var \ValueObject\TableObject VO do objeto.
    */
   public $tableObjectVO_;

   /**
    *
    * @var bool Indica se o usuário pode escrever no objeto.
    */
   public $canWrite_;

   /**
    * Construtor padrão.
    * Inicializa as variáveis da classe.
    *
    * @param \ValueObject\TableObject $tableObjectVO
    *           Objeto contendo as informações do objeto no banco de dados.
    * @param bool $canWrite
    *           [optional] Indica se o usuário pode escrever sobre o objeto.
    */
   public function __construct( \ValueObject\TableObject $tableObjectVO, bool $canWrite = false)
   {
      $this->tableObjectVO_ = $tableObjectVO;
      $this->canWrite_ = $canWrite;
   }

   /**
    * Função para clonar um Asset.
    */
   public function __clone()
   {
      $this->tableObjectVO_ = clone $this->getTableObjectVO();
      $this->canWrite_ = clone $this->canWrite();
   }

   /**
    * Indica se o usuário pode modificar o objeto.
    *
    * @return bool Bool indicando se o user pode modificar o objeto.
    */
   public function canWrite(): bool
   {
      return $this->canWrite_;
   }

   /**
    * Obtém o VO do objeto.
    *
    * @return \ValueObject\TableObject Objeto contendo as informações do objeto no banco de dados.
    */
   public function getTableObjectVO(): \ValueObject\TableObject
   {
      return $this->tableObjectVO_;
   }
}

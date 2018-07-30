<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers\WebUserProvider.php";

/**
 * Testa as funções públicas de WebUserProvider.
 */
class WebUserProviderTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\WebUserProvider
    */
   private $provider_;

   /**
    * Objeto de teste.
    *
    * @var \ValueObject\WebUser
    */
   private $webUserVO_;

   public function setUp()
   {
      $this->provider_ = \DBLib\WebUserProvider::getInstance();

      $webUserVO = new ValueObject\WebUser();
      $webUserVO->login = "teste";
      $webUserVO->name = "teste";
      $webUserVO->email = "teste";
      $webUserVO->pass = "pass";
      $webUserVO->isadmin = 1;
      $this->webUserVO_ = $webUserVO;
   }

   public function testGetWebUser()
   {
      $this->assertInstanceOf( "\DBLib\WebUser", $this->provider_->getWebUser( 1 ) );

      $this->assertTrue( $this->provider_->getWebUser( 1 )->isAdmin() );
      $this->assertFalse( $this->provider_->getWebUser( 3 )->isAdmin() );

      $this->assertNull( $this->provider_->getWebUser( -1 ) );
   }

   public function testGetWebUserByLogin()
   {
      $allUsers = $this->provider_->getAllWebUsers();

      // verifica que pode pegar todos os users através de seus logins, e o resultado é o próprio user
      foreach( $allUsers as &$user )
      {
         $this->assertEquals( $user, $this->provider_->getWebUserByLogin( $user->getWebUserVO()->login ) );
      }

      $this->assertNull( $this->provider_->getWebUserByLogin( "nonExistant" ) );
   }

   public function testGetAllWebUsers()
   {
      foreach( $this->provider_->getAllWebUsers() as $wu )
      {
         $this->assertInstanceOf( "\DBLib\WebUser", $wu );
      }
   }

   public function testGetWebUsers()
   {
      $allUsers = $this->provider_->getAllWebUsers();
      $ids = array_keys( $allUsers );

      // verifica que está na lista de ids buscados
      foreach( $this->provider_->getWebUsers( $ids ) as $wu )
      {
         $this->assertTrue( in_array( $wu->getWebUserVO()->idwebuser, $ids ) );
         $this->assertInstanceOf( "\DBLib\WebUser", $wu );
      }

      // verifica que são os mesmos arrays
      $this->assertEquals( count( $allUsers ), count( $this->provider_->getWebUsers( $ids ) ) );
      $this->assertEquals( $allUsers, $this->provider_->getWebUsers( $ids ) );

      // verifica que se não forem passados ids, retorna array vazio
      $this->assertEmpty( $this->provider_->getWebUsers( array () ) );
   }

   public function testInsertWebUser()
   {
      $this->assertTrue( $this->provider_->insertWebUser( $this->webUserVO_ ) );
      $newUser = $this->provider_->getWebUserByLogin( "teste" );

      // remove o usuário
      $this->provider_->removeWebUser( $newUser->getWebUserVO() );

      // verifica que user normal não pode inserir um user
      $this->assertFalse( $this->provider_->insertWebUser( $this->webUserVO_ ) );
      $this->assertNotEquals( "", $this->provider_->getLastError() );
   }

   public function testUpdateWebUser()
   {
      $this->assertTrue( $this->provider_->insertWebUser( $this->webUserVO_ ) );
      $newUser = $this->provider_->getWebUserByLogin( "teste" );

      // atualiza um campo
      $newName = "updatedtestuser";
      $updatedVO = $newUser->getWebUserVO();
      $updatedVO->name = $newName;

      // verifica a persistência da atualização
      $this->assertTrue( $this->provider_->updateWebUser( $updatedVO ) );
      $this->assertEquals( $this->provider_->getWebUserByLogin( "teste" )->getWebUserVO()->name, $newName );

      // remove o usuário
      $this->provider_->removeWebUser( $newUser->getWebUserVO() );
   }

   public function testDeleteWebUser()
   {
      $this->assertTrue( $this->provider_->insertWebUser( $this->webUserVO_ ) );

      $testUser = $this->provider_->getWebUserByLogin( "teste" );
      $this->assertTrue( $this->provider_->removeWebUser( $testUser->getWebUserVO() ) );

      // somente admin pode remover um usuário
      $this->assertFalse( $this->provider_->removeWebUser( $testUser->getWebUserVO() ) );
      $this->assertNotEquals( "", $this->provider_->getLastError() );
   }
}
?>